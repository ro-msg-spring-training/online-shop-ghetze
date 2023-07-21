package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.OrderDetail;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.exceptions.OrderException;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.services.interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl  implements OrderService {
	private static final String MESSAGE_PRODUCTS_NO_STOCKS = "There are no sufficient stocks for your products";
	private static final String MESSAGE_NO_LOCATION_WITH_ALL_PRODUCTS = "There is no location which has all products on stock";
	@Autowired
	private final OrderRepository orderRepository;
	@Autowired
	private final StockRepository stockRepository;


	@Override
	@Transactional
	public Order createOrder(Order order) {

		//get stocks with sufficient quantities for all products from the order
		List<Stock> stocks = getAvailableStocks(order);

		stocks.forEach(stock -> {
			for (OrderDetail orderDetail : order.getOrderDetailsList()) {
				orderDetail.setOrder(order);
				orderDetail.setShippedFrom(stock.getLocation());

				if (orderDetail.getProduct().getId().equals(stock.getProduct().getId()))
				{
					int quantity = stock.getQuantity() - orderDetail.getQuantity();
					stock.setQuantity(quantity);

					//update stocks with subtracted value
					stockRepository.save(stock);
				}
			}
		});

		//Save order and order details
		orderRepository.saveAndFlush(order);

		return order;
	}

	private List<Stock> getAvailableStocks(Order order)
	{
		List<Stock> availableStocks = new ArrayList<>();
		List<Stock> allStocksWithAllProducts = new ArrayList<>();

		order.getOrderDetailsList().forEach(orderDetail -> {
				var productId = orderDetail.getProduct().getId();

				//get a list with all locations with sufficient stock for a product order desc by quantity.
				List<Stock> productStocks = stockRepository.findStockByProductAndQuantityOrderDescByQuantity(productId, orderDetail.getQuantity());
				if (productStocks.isEmpty())
				{
					throw new OrderException(MESSAGE_PRODUCTS_NO_STOCKS);
				}
				allStocksWithAllProducts.addAll(productStocks);
			}
		);

		//group all stocks by location_id
		Map<UUID, List<Stock>> stocksGroupedByLocationId = allStocksWithAllProducts.stream().collect(Collectors.groupingBy(stock->stock.getLocation().getId()));

		//get the first location_id which contain all the products.
		for (Map.Entry<UUID, List<Stock>> entry : stocksGroupedByLocationId.entrySet()) {
			if (entry.getValue().size() == order.getOrderDetailsList().size()) {
				availableStocks.addAll(entry.getValue());
				break;
			}
		}

		//in case there is not a single location with all products on stock throw OrderException
		if (availableStocks.isEmpty())
		{
			throw new OrderException(MESSAGE_NO_LOCATION_WITH_ALL_PRODUCTS);
		}

		return availableStocks;
	}
}

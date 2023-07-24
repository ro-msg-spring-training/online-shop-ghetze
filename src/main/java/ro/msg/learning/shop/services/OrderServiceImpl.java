package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.LocationConfiguration;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.OrderDetail;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.services.interfaces.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl  implements OrderService {
	@Autowired
	private final OrderRepository orderRepository;
	@Autowired
	private final StockRepository stockRepository;

	@Autowired
	private final LocationConfiguration locationConfiguration;


	@Override
	@Transactional
	public Order createOrder(Order order) {

		order.setCreatedAt(LocalDateTime.now());

		//get stocks with sufficient quantities for all products from the order
		List<Stock> stocks = locationConfiguration.locationStrategy().getAvailableStocks(order);


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
}

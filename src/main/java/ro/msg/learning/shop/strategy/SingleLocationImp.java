package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.exceptions.OrderException;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocationImp implements LocationStrategy{
	@Autowired
	private StockRepository stockRepository;

	private static final String MESSAGE_PRODUCTS_NO_STOCKS = "There are no sufficient stocks for your products";
	private static final String MESSAGE_NO_LOCATION_WITH_ALL_PRODUCTS = "There is no location which has all products on stock";
	@Override
	public List<Stock> getAvailableStocks(Order order) {
		List<Stock> availableStocks = new ArrayList<>();
		List<Stock> allStocksWithAllProducts = new ArrayList<>();

		order.getOrderDetailsList().forEach(orderDetail -> {
				var productId = orderDetail.getProduct().getId();

				//get a list with all locations with sufficient stock for a product.
				List<Stock> productStocks = stockRepository.findStockByProductAndQuantity(productId, orderDetail.getQuantity());
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
		Map.Entry<UUID, List<Stock>> matchingEntry = stocksGroupedByLocationId.entrySet().stream()
			.filter(entry -> entry.getValue().size() == order.getOrderDetailsList().size())
			.findFirst()
			.orElse(null);

		if (matchingEntry != null) {
			availableStocks.addAll(matchingEntry.getValue());
		}
		else
		{
			throw new OrderException(MESSAGE_NO_LOCATION_WITH_ALL_PRODUCTS);
		}

		return availableStocks;
	}
}

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

@AllArgsConstructor
@NoArgsConstructor
public class MostAbundantLocationImpl implements LocationStrategy{
	@Autowired
	private StockRepository stockRepository;

	private static final String MESSAGE_PRODUCTS_NO_STOCKS = "There are no sufficient stocks for your products";

	@Override
	public List<Stock> getAvailableStocks(Order order) {
		List<Stock> allStocks = new ArrayList<>();
		order.getOrderDetailsList().forEach(product -> {
				var productId = product.getProduct().getId();

			    List<Stock> productStocks = stockRepository.findStockByProductAndQuantityOrderDescByQuantity(productId, product.getQuantity());

				//the products are already ordered by quantity desc, so you can get the first in the list.
				if (!productStocks.isEmpty()) {
					allStocks.add(productStocks.get(0));
				}
				else
				{
					throw new OrderException(MESSAGE_PRODUCTS_NO_STOCKS);
				}
			}
		);

		return allStocks;
	}
}

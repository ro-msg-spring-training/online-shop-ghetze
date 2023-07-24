package ro.msg.learning.shop.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.Stock;

import java.util.List;

@Component
public interface LocationStrategy {
	List<Stock> getAvailableStocks(Order order);
}

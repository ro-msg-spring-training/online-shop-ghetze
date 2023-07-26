package ro.msg.learning.shop.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.exceptions.OrderException;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.utils.TestingUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleLocationImplTest {
	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private SingleLocationImp locationStrategy ;

	@Test
	void findStocksForOrder_whenThereIsNotASingleLocationForAllProducts_shouldReturnOrderException() {

		var order = TestingUtils.createOrder(
			UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"),
			UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1232"));

		//there are no stocks available.
		when(stockRepository.findStockByProductAndQuantity(any(),any())).thenReturn(List.of());

		//OrderException exception should be thrown
		assertThrows(OrderException.class,() -> locationStrategy.getAvailableStocks(order));
	}

	@Test
	void findStocksForOrder_whenThereAreStocksInSameLocation_shouldReturn2ItemsList() {
		var product3UUID = UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1233");
		var product4UUID = UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1234");

		var location1UUID = UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231");

		var order = TestingUtils.createOrder(product3UUID,product4UUID);

		//there are 2 stocks available one for each product.
		when(stockRepository.findStockByProductAndQuantity(product3UUID,1)).thenReturn(TestingUtils.returnStockList(product3UUID,location1UUID));
		when(stockRepository.findStockByProductAndQuantity(product4UUID,1)).thenReturn(TestingUtils.returnStockList(product4UUID,location1UUID));

		List<Stock> availableStocks = locationStrategy.getAvailableStocks(order);
		assertEquals(2, availableStocks.size());
	}
}

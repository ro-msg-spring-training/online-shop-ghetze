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
public class MostAbundantLocationImplTest {
	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private MostAbundantLocationImpl locationStrategy ;

	@Test
	public void findStocksForOrder_whenThereIsNotASingleLocationForAllProducts_shouldReturnOrderException() {

		//create order with 2 products
		var order = TestingUtils.createOrder(
			UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"),
			UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1232"));

		//there are no stocks available.
		when(stockRepository.findStockByProductAndQuantityOrderDescByQuantity(any(),any())).thenReturn(List.of());

		//OutOfStockException exception should be thrown
		assertThrows(OrderException.class,() -> locationStrategy.getAvailableStocks(order));
	}

	@Test
	public void findStocksForOrder_whenThereAreMultipleStocks_shouldReturnBiggestStock() {

		var product1UUID = UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231");

		//create order with 1 product and quantity 1
		var order = TestingUtils.createOrderWithOneProductAndGivenQuantity(product1UUID,1);

		//there are 2 stocks available one for the product with 2 diferrent quantities (10 and 20)
		when(stockRepository.findStockByProductAndQuantityOrderDescByQuantity(product1UUID,1)).thenReturn(TestingUtils.returnStocksWithDifferentQuantities(false,product1UUID));

		List<Stock> availableStocks = locationStrategy.getAvailableStocks(order);
		//only one stock should be returned.
		assertEquals(1, availableStocks.size());
		//check to see if the selected stock is the one with thew bigest quantity = 20
		assertEquals(20, (int) availableStocks.get(0).getQuantity());

	}
}

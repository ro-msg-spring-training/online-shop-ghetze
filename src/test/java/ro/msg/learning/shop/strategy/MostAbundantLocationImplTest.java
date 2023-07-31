package ro.msg.learning.shop.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.OrderDetail;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.exceptions.OrderException;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.utils.TestingUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MostAbundantLocationImplTest {
	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private MostAbundantLocationImpl locationStrategy ;

	@Test
	void findStocksForOrder_whenThereIsNotASingleLocationForAllProducts_shouldReturnOrderException() {

		//create order with 2 products
		var order = TestingUtils.orderEntityWithPhoneAndLaptopProducts;

		//there are no stocks available.
		when(stockRepository.findStockByProductAndQuantityOrderDescByQuantity(any(),any())).thenReturn(List.of());

		//OutOfStockException exception should be thrown
		assertThrows(OrderException.class,() -> locationStrategy.getAvailableStocks(order));
	}

	@Test
	void findStocksForOrder_whenThereAreMultipleStocks_shouldReturnBiggestStock() {

		Order orderWithSamsumgMobilePhoneProductAndQuantity1 = new Order(
			TestingUtils.customer,
			null,
			"Romania",
			"Floresti",
			"Cluj-Napoca",
			"Sub Cetate 25R",
			List.of(
				new OrderDetail(null,TestingUtils.productSamsungMobilePhone,1,null)
			)
		);

		List<Stock> stocksWithDifferentQuantities  = List.of(
			TestingUtils.stockSamsungMobilePhoneQuantity30Location1,
			TestingUtils.stockSamsungMobilePhoneQuantity10Location1
		);//	}

		//there are 2 stocks available one for the product with 2 different quantities (10 and 20)
		when(stockRepository.findStockByProductAndQuantityOrderDescByQuantity(TestingUtils.productSamsungMobilePhone.getId(),1)).thenReturn(stocksWithDifferentQuantities);

		List<Stock> availableStocks = locationStrategy.getAvailableStocks(orderWithSamsumgMobilePhoneProductAndQuantity1);
		//only one stock should be returned.
		assertEquals(1, availableStocks.size());
		//check to see if the selected stock is the one with the biggest quantity = 30
		assertEquals(30, (int) availableStocks.get(0).getQuantity());

	}
}

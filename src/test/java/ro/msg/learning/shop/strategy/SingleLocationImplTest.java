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

		var order = TestingUtils.orderEntityWithPhoneAndLaptopProducts;

		//there are no stocks available.
		when(stockRepository.findStockByProductAndQuantity(any(),any())).thenReturn(List.of());

		//OrderException exception should be thrown
		assertThrows(OrderException.class,() -> locationStrategy.getAvailableStocks(order));
	}

	@Test
	void findStocksForOrder_whenThereAreStocksInSameLocation_shouldReturn2ItemsList() {

		var order = TestingUtils.orderEntityWithPhoneAndLaptopProducts;

		List<Stock> stockForSamsungMobilePhone = List.of(
			TestingUtils.stockSamsungMobilePhoneQuantity30Location1
		);

		List<Stock> stockForLenovoLaptop = List.of(
			TestingUtils.stockLenovoLaptopQuantity20Location1
		);

		//there are 2 stocks available one for each product.
		when(stockRepository.findStockByProductAndQuantity(TestingUtils.productSamsungMobilePhone.getId(),1)).thenReturn(stockForSamsungMobilePhone);
		when(stockRepository.findStockByProductAndQuantity(TestingUtils.productLenovoLaptop.getId(),1)).thenReturn(stockForLenovoLaptop);

		List<Stock> availableStocks = locationStrategy.getAvailableStocks(order);
		assertEquals(2, availableStocks.size());
	}
}

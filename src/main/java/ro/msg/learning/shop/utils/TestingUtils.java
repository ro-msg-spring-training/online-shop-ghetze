package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.entitites.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestingUtils {
	private static final UUID customerId = UUID.fromString("631e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID location1Id = UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID stock2Id = UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1232");

	public static Order createOrder(UUID productId1, UUID productId2) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		OrderDetail orderDetail1 = new OrderDetail();
		var product1 = new Product();
		product1.setId(productId1);
		orderDetail1.setProduct(product1);
		orderDetail1.setQuantity(1);
		orderDetails.add(orderDetail1);

		if (productId2 != null) {
			OrderDetail orderDetail2 = new OrderDetail();
			var product2 = new Product();
			product2.setId(productId2);
			orderDetail2.setProduct(product2);
			orderDetail2.setQuantity(1);
			orderDetails.add(orderDetail2);
		}

		Order order = new Order();

		order.setOrderDetailsList(orderDetails);
		order.setCreatedAt(LocalDateTime.now());

		order.setAddressCity("a");
		order.setAddressCounty("b");
		order.setAddressCountry("c");
		order.setAddressStreet("d");

		Customer customer = new Customer();
		customer.setId(customerId);

		order.setCustomer(customer);
		return order;
	}


	public static Order createOrderWithOneProductAndGivenQuantity(UUID productId1,Integer quantity) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		OrderDetail orderDetail1 = new OrderDetail();
		var product1 = new Product();
		product1.setId(productId1);
		orderDetail1.setProduct(product1);
		orderDetail1.setQuantity(quantity);
		orderDetails.add(orderDetail1);

		Order order = new Order();

		order.setOrderDetailsList(orderDetails);
		order.setCreatedAt(LocalDateTime.now());

		order.setAddressCity("a");
		order.setAddressCounty("b");
		order.setAddressCountry("c");
		order.setAddressStreet("d");

		Customer customer = new Customer();
		customer.setId(customerId);

		order.setCustomer(customer);
		return order;
	}

	public static List<Stock> returnStocksWithDifferentQuantities(boolean sameLocation, UUID productIdToBeReturned) {
		List<Stock> returnedStocks = new ArrayList<>();

		Stock stock1 = new Stock();
		Product prod1 = new Product();
		prod1.setId(productIdToBeReturned);
		Location loc1 = new Location();
		loc1.setId(location1Id);
		stock1.setLocation(loc1);
		stock1.setProduct(prod1);
		stock1.setQuantity(20);
		returnedStocks.add(stock1);

		Stock stock2 = new Stock();
		Product prod2 = new Product();
		prod2.setId(productIdToBeReturned);
		Location loc2 = new Location();
		loc2.setId(sameLocation ? loc1.getId() : stock2Id);
		stock2.setLocation(loc2);
		stock2.setProduct(prod2);
		stock2.setQuantity(10);
		returnedStocks.add(stock2);
		return returnedStocks;
	}

	public static List<Stock> returnStockList(UUID productId, UUID locationId) {
		List<Stock> returnedStocks = new ArrayList<>();
		Stock stock = new Stock();
		Product prod1 = new Product();
		prod1.setId(productId);
		Location loc1 = new Location();
		loc1.setId(locationId);
		stock.setLocation(loc1);
		stock.setProduct(prod1);
		stock.setQuantity(20);
		returnedStocks.add(stock);
		return returnedStocks;
	}
}

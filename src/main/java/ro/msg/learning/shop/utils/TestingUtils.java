package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.entitites.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestingUtils {
	private static final UUID customerId = UUID.fromString("631e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID location1Id = UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID location2Id = UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1232");
	private static final UUID stock1Id = UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID stock2Id = UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1232");
	private static final UUID supplier1Id = UUID.fromString("131e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID supplier2Id = UUID.fromString("131e4cdd-bb78-4769-a0c7-cb948a9f1232");
	private static final UUID productCategory1Id = UUID.fromString("231e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID productCategory2Id = UUID.fromString("231e4cdd-bb78-4769-a0c7-cb948a9f1232");

	private static final UUID product1Id = UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231");
	private static final UUID product2Id = UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1232");

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

	//Use this method to populate the database from code.
	//It wil return almost all entities necessary to perform integration test.
	public static List<EntityWithID> getEntities() {
		List<EntityWithID> entities = new ArrayList<>();
		//supplier
		Supplier supplier1 = new Supplier();
		supplier1.setId(supplier1Id);
		supplier1.setName("Supplier1");
		entities.add(supplier1);

		Supplier supplier2 = new Supplier();
		supplier2.setId(supplier2Id);
		supplier2.setName("Supplier2");
		entities.add(supplier2);

		//product category
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setId(productCategory1Id);
		productCategory1.setName("category1");
		productCategory1.setDescription("category1 description");
		entities.add(productCategory1);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setId(productCategory2Id);
		productCategory2.setName("category2");
		productCategory2.setDescription("category2 description");
		entities.add(productCategory2);

		//products
		Product product1 = new Product();
		product1.setId(product1Id);
		product1.setName("Product1");
		product1.setDescription("Product 1 description");
		product1.setPrice(BigDecimal.valueOf(10.10));
		product1.setWeight(Double.valueOf(81.11));
		product1.setSupplier(supplier1);
		product1.setCategory(productCategory1);
		product1.setImageUrl("http:test.com/1.jpg");
		entities.add(product1);

		Product product2 = new Product();
		product2.setId(product2Id);
		product2.setName("Product2");
		product2.setDescription("Product 2 description");
		product2.setPrice(BigDecimal.valueOf(2.2));
		product2.setWeight(Double.valueOf(82.22));
		product2.setSupplier(supplier2);
		product2.setCategory(productCategory2);
		product2.setImageUrl("http:test.com/2.jpg");
		entities.add(product2);

		//Locations
		Location loc1 = new Location();
		loc1.setId(location1Id);
		loc1.setName("ClujNapocaCenter");
		loc1.setAddressCity("Cluj-Napoca");
		loc1.setAddressCounty("Cluj");
		loc1.setAddressCountry("Romania");
		loc1.setAddressStreet("croitorilor nr 11-12");
		entities.add(loc1);

		Location loc2 = new Location();
		loc2.setId(location2Id);
		loc2.setName("MetropolitanArea");
		loc2.setAddressCity("Floresti");
		loc2.setAddressCounty("Cluj");
		loc2.setAddressCountry("Romania");
		loc2.setAddressStreet("str eroilor 20");
		entities.add(loc2);

		//stocks
		Stock stock1 = new Stock();
		stock1.setId(stock1Id);
		stock1.setLocation(loc1);
		stock1.setProduct(product1);
		stock1.setQuantity(10);
		entities.add(stock1);

		Stock stock2 = new Stock();
		stock2.setId(stock2Id);
		stock2.setLocation(loc2);
		stock2.setProduct(product2);
		stock2.setQuantity(20);
		entities.add(stock2);

		//create 1 customers
		Customer customer1 = new Customer();
		customer1.setId(customerId);
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customer1.setUsername("johndoe");
		customer1.setPassword("asdasasasa");
		customer1.setEmailAddress("johndoe@microsoft.com");
		entities.add(customer1);

		return entities;

	}
}

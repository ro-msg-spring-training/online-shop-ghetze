package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entitites.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TestingUtils {
		public static final Customer customer = new Customer(
		UUID.fromString("631e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"Sean",
		"John",
		"seanjohn",
		"$2a$04$DGY67JmLYM08juIEw1dMz.0gIp8lyiyxzTL0pp5BV9e5evxq/DO0q",
		"seanjohn@yahoo.com");
	// BCryptPasswordEncoder : sa


	public static final  Location location1Floresti = new Location (
		UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"Floresti",
		"Romania",
		"Floresti",
		"Cluj",
		"eroilor 45");
	public static final  Location location2Cluj = new Location (
		UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1232"),
		"Cluj",
		"Romania",
		"Cluj-Napoca",
		"Cluj",
		"eroilor 56");
	public static final ProductCategory laptopProductCategory = new ProductCategory(
		UUID.fromString("231e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"laptop",
		"laptop category");
	public static final ProductCategory mobileLaptopCategory = new ProductCategory(
		UUID.fromString("231e4cdd-bb78-4769-a0c7-cb948a9f1232"),
		"mobile",
		"mobile category");

	public static final Supplier supplier1Emag = new Supplier(
		UUID.fromString("131e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"Emag");
	public static final Supplier supplier2Altex = new Supplier(
		UUID.fromString("131e4cdd-bb78-4769-a0c7-cb948a9f1232"),
		"Altex");

	public static final Product productSamsungMobilePhone = new Product(
		UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"samsung galaxy j10",
		"5g,WIFI,Mirroring",
		BigDecimal.valueOf(2.2),
		Double.valueOf(82.2),
		"http:test.com/1.jpg",
		supplier1Emag,
		mobileLaptopCategory
	);

	public static final Product productLenovoLaptop = new Product(
		UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1232"),
		"lenovo 1450",
		"touch,WIFI,gigabit ethernet",
		BigDecimal.valueOf(10.10),
		Double.valueOf(81.11),
		"http:test.com/2.jpg",
		supplier1Emag,
		laptopProductCategory
	);
	public static final Stock stockSamsungMobilePhoneQuantity10Location1 = new Stock(
		UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		productSamsungMobilePhone,
		location1Floresti,
						10);

	public static final Stock stockSamsungMobilePhoneQuantity30Location1 = new Stock(
		UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1232"),
		productSamsungMobilePhone,
		location1Floresti,
		30);
	public static final Stock stockLenovoLaptopQuantity20Location2 = new Stock(
		UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1233"),
		productLenovoLaptop, location2Cluj,
		20);

	public static final Stock stockLenovoLaptopQuantity20Location1 = new Stock(
		UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1234"),
		productLenovoLaptop, location1Floresti,
		20);


	public static final Order orderEntityWithPhoneAndLaptopProducts = new Order(
		customer,
		null,
		"Romania",
		"Floresti",
		"Cluj-Napoca",
		"Sub Cetate 25R",
		List.of(
			new OrderDetail(null, productSamsungMobilePhone,1,null),
			new OrderDetail(null, productLenovoLaptop,1,null)
		)
	);

	public static final OrderDTO orderDTOWith2productsAndQuantity1 = new OrderDTO(
		UUID.fromString("777e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"Romania",
		"Floresti",
		"Sub cetate",
		"Cluj",
		UUID.fromString("631e4cdd-bb78-4769-a0c7-cb948a9f1231"),
			LocalDateTime.now(),
			List.of(
				new OrderDetailDTO(productSamsungMobilePhone.getId(),1),
				new OrderDetailDTO(productLenovoLaptop.getId(),1)
			)

	);

	public static final OrderDTO orderDTOWith2productsAndQuantityBiggerThenStocks = new OrderDTO(
		UUID.fromString("777e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		"Romania",
		"Floresti",
		"Sub cetate",
		"Cluj",
		UUID.fromString("631e4cdd-bb78-4769-a0c7-cb948a9f1231"),
		LocalDateTime.now(),
		List.of(
			new OrderDetailDTO(productSamsungMobilePhone.getId(),99999),
			new OrderDetailDTO(productLenovoLaptop.getId(),99999)
		)

	);

}

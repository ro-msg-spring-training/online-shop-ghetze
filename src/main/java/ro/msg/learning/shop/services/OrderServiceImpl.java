package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.LocationConfiguration;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;
import ro.msg.learning.shop.services.interfaces.OrderService;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.OrderDetail;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl  implements OrderService {
	@Autowired
	private final OrderRepository orderRepository;
	@Autowired
	private final StockRepository stockRepository;
	@Autowired
	private final ProductRepository productRepository;

	@Autowired
	private final LocationConfiguration locationConfiguration;

	private static final String PRODUCTS_NOT_AVAILABLE = "The following products are not available :";


	@Override
	@Transactional
	public Order createOrder(Order order) {

		//check to see if the order contains unavailable products.
		checkForUnavailableProducts(order);

		order.setCreatedAt(LocalDateTime.now());

		//get stocks with sufficient quantities for all products from the order
		List<Stock> stocks = locationConfiguration.locationStrategy().getAvailableStocks(order);


		stocks.forEach(stock -> {
			for (OrderDetail orderDetail : order.getOrderDetailsList()) {
				orderDetail.setOrder(order);
				orderDetail.setShippedFrom(stock.getLocation());

				if (orderDetail.getProduct().getId().equals(stock.getProduct().getId()))
				{
					int quantity = stock.getQuantity() - orderDetail.getQuantity();
					stock.setQuantity(quantity);

					//update stocks with subtracted value
					stockRepository.save(stock);
				}
			}
		});

		//Save order and order details
		orderRepository.saveAndFlush(order);

		return order;
	}

	//Checks for unavailable products and return a ResourceNotFoundException with the not found product ids.
	private void checkForUnavailableProducts(Order order) {
		//filter orderedProductIds
		List<UUID> orderedProducts= order.getOrderDetailsList().stream().map(item->item.getProduct().getId()).collect(Collectors.toList());

		//check products repo for existing products, the method expects List<String>, so it will work on both sql and h2 databases.
		List<byte[]> foundProductsInBytes = productRepository.findAllByIds(orderedProducts.stream().map(item->toBytes(item)).collect(Collectors.toList()));

		var foundProducts = foundProductsInBytes.stream().map(item->fromBytes(item)).collect(Collectors.toList());

		//filter not found products
		List<UUID> notFoundProductsIds = orderedProducts.stream()
			.filter(product -> !foundProducts.contains(product))
			.collect(Collectors.toList());

		//if there are product ids not found in the products repo throw exception
		if (notFoundProductsIds.size() > 0)
			throw new ResourceNotFoundException(
				PRODUCTS_NOT_AVAILABLE + notFoundProductsIds.stream().map(item->item.toString()).collect(Collectors.joining(", "))
			);
	}

	private byte[] toBytes(UUID uuid) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return bb.array();
	}

	private UUID fromBytes(byte[] bytes) {
		if (bytes == null || bytes.length != 16) {
			throw new IllegalArgumentException("Invalid byte array to convert to UUID.");
		}

		ByteBuffer bb = ByteBuffer.wrap(bytes);
		long mostSigBits = bb.getLong();
		long leastSigBits = bb.getLong();

		return new UUID(mostSigBits, leastSigBits);
	}
}

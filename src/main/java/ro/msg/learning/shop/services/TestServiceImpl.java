package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.services.interfaces.TestService;
import ro.msg.learning.shop.entitites.*;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.utils.TestingUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
	@Autowired
	private final ProductRepository productRepository;
	@Autowired
	private final LocationRepository locationRepository;
	@Autowired
	private final ProductCategoryRepository productCategoryRepository;
	@Autowired
	private final SupplierRepository supplierRepository;
	@Autowired
	private final CustomerRepository customerRepository;
	@Autowired
	private final StockRepository stockRepository;
	@Autowired
	private final OrderRepository orderRepository;
	@Autowired
	private final OrderDetailRepository orderDetailRepository;

	@Override
	@Transactional
	public void populateDatabase() {

		List<EntityWithID> entities = TestingUtils.getEntities();
		entities.forEach(entity -> saveEntity(entity));
	}

	@Override
	@Transactional
	public void clearDatabase() {
			orderDetailRepository.deleteAllInBatch();
			orderRepository.deleteAllInBatch();
			customerRepository.deleteAllInBatch();
			stockRepository.deleteAllInBatch();
			locationRepository.deleteAllInBatch();
			productRepository.deleteAllInBatch();
			productCategoryRepository.deleteAllInBatch();
			supplierRepository.deleteAllInBatch();
	}

	private void saveEntity(EntityWithID entity) {
		if (entity instanceof Supplier) {
			var savedEntity = supplierRepository.save((Supplier) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof ProductCategory) {
			var savedEntity = productCategoryRepository.save((ProductCategory) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof Product) {
			var savedEntity = productRepository.save((Product) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof Customer) {
			var savedEntity = customerRepository.save((Customer) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof Location) {
			var savedEntity = locationRepository.save((Location) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof Stock) {
			var savedEntity = stockRepository.save((Stock) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof Order) {
			var savedEntity = orderRepository.save((Order) entity);
			entity.setId(savedEntity.getId());
		} else if (entity instanceof OrderDetail) {
			var savedEntity = orderDetailRepository.save((OrderDetail) entity);
			entity.setId(savedEntity.getId());
		} else throw new IllegalArgumentException("Unsupported entity type: " + entity.getClass().getSimpleName());
	}


}

package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.services.interfaces.TestService;
import ro.msg.learning.shop.entitites.*;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.utils.TestingUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
@Profile("test")
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
		saveEntity(TestingUtils.supplier1Emag,supplierRepository);
		saveEntity(TestingUtils.supplier2Altex,supplierRepository);
		saveEntity(TestingUtils.laptopProductCategory,productCategoryRepository);
		saveEntity(TestingUtils.mobileLaptopCategory,productCategoryRepository);
		saveEntity(TestingUtils.productSamsungMobilePhone,productRepository);
		saveEntity(TestingUtils.productLenovoLaptop, productRepository);
		saveEntity(TestingUtils.customer,customerRepository);
		saveEntity(TestingUtils.location2Cluj,locationRepository);
		saveEntity(TestingUtils.location1Floresti,locationRepository);
		saveEntity(TestingUtils.stockSamsungMobilePhoneQuantity10Location1,stockRepository);
		saveEntity(TestingUtils.stockLenovoLaptopQuantity20Location1,stockRepository);
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

	// Generic method to save an entity and set its ID
	public <T extends EntityWithID> void saveEntity(T entity, JpaRepository<T, UUID> repository) {
		T savedEntity = repository.save(entity);
		entity.setId(savedEntity.getId());
	}
}

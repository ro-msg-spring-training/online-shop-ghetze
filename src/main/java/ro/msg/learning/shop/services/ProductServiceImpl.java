package ro.msg.learning.shop.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entitites.Product;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.repositories.ProductRepository;
import ro.msg.learning.shop.services.interfaces.ProductService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	private static final String PRODUCT_NOT_FOUND_MESSAGE = "Requested product id was not found: ";
	@Autowired
	private final ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(UUID id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id);
		}
		return product;
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		if(productRepository.findById(product.getId()).orElse(null) == null) {
			throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + product.getId());
		}
		return productRepository.save(product);
	}

	@Override
	public void delete(UUID id) {
		if (productRepository.findById(id).orElse(null) == null)
		{
			throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_MESSAGE + id);
		}
		productRepository.deleteById(id);
	}
}

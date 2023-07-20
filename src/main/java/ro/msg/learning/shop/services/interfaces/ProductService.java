package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.entitites.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {

	List<Product> getAll();

	Product getById(UUID id);

	Product create(Product product);

	Product update (Product product);

	void delete(UUID id);

}

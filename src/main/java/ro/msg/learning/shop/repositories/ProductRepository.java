package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entitites.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	//We are using byte[] instead of UUID so UUID conversion will not be a problem when
	//working with both PostgresSQL (for production) and H2 in memory database (for testing).
	@Query(value = "SELECT p.id FROM Product p WHERE p.id IN (:productIds)")
	List<UUID> findExistingProductUUIDs(@Param("productIds") List<UUID> productIds);

}

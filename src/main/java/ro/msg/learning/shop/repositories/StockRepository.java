package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entitites.Stock;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

	@Query(value = "Select * from stock where product_id = :productId AND quantity >= :quantity order by quantity desc",nativeQuery = true)
	List<Stock> findStockByProductAndQuantityOrderDescByQuantity(@Param("productId") UUID productId, @Param("quantity") Integer quantity);

}

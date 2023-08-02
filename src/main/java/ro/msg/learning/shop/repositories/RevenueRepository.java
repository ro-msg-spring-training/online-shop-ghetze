package ro.msg.learning.shop.repositories;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entitites.Revenue;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, UUID> {
	@Query("SELECT r.id as locationId, SUM(od.quantity * p.price) as sum " +
		"FROM Order o " +
		"JOIN o.orderDetailsList od " +
		"JOIN od.product p " +
		"JOIN od.shippedFrom r " +
		"WHERE DATE(o.createdAt) = :inputDate " +
		"GROUP BY r.id")
	List<Tuple> aggregateSalesRevenuesByLocation(@Param("inputDate") LocalDate inputDate);

	List<Revenue> findAllByDate(LocalDate date);

}

package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.entitites.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface RevenueService {
	void aggregateSalesRevenuesByLocation(LocalDate date);

	List<Revenue> findAllByDate(LocalDate date);
}

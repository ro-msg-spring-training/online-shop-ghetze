	package ro.msg.learning.shop.services;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.services.interfaces.RevenueService;
import ro.msg.learning.shop.entitites.Location;
import ro.msg.learning.shop.entitites.Revenue;
import ro.msg.learning.shop.repositories.RevenueRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private final RevenueRepository revenueRepository;

	@Override
	@Transactional
	public void aggregateSalesRevenuesByLocation(LocalDate date) {
		var today = LocalDate.now().minusDays(1);
		List<Tuple> revenuesAggregated = revenueRepository.aggregateSalesRevenuesByLocation(today);

		List<Revenue> revenues = revenuesAggregated.stream()
			.map(tuple -> new Revenue(new Location((UUID) tuple.get("locationId")),LocalDate.now(), (BigDecimal) tuple.get("sum")))
			.toList();

		if (!revenues.isEmpty()) {
			revenueRepository.saveAll(revenues);
		}
	}

	@Override
	public List<Revenue> findAllByDate(LocalDate date)
	{
		return revenueRepository.findAllByDate(date);
	}

}

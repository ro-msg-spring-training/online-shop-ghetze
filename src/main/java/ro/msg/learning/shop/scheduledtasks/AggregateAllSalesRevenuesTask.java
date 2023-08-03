package ro.msg.learning.shop.scheduledtasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.services.interfaces.RevenueService;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Slf4j
public class AggregateAllSalesRevenuesTask {

	@Autowired
	private final RevenueService revenueService;

	@Async
	@Scheduled(cron = "${shop.revenues-aggregation.cron.expression}")
	public void executeRevenueAggregationForToday()
	{
		try {
			log.info("Revenues aggregation started.");
			revenueService.aggregateSalesRevenuesByLocation(LocalDate.now());
			log.info("Revenues aggregation finished successfully.");
		}
		catch (Exception ex){
			log.error(ex.getMessage());
		}
	}
}

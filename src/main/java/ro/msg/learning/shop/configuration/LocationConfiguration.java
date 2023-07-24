package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundantLocationImpl;
import ro.msg.learning.shop.strategy.SingleLocationImp;
import ro.msg.learning.shop.strategy.StrategyTypeEnum;

@Configuration
public class LocationConfiguration {
	@Value("${strategy}")
	StrategyTypeEnum strategy;

	@Bean
	public LocationStrategy locationStrategy() {

		return switch (strategy) {
			case MOST_ABUNDANT -> new MostAbundantLocationImpl();
			default -> new SingleLocationImp();
		};
	}
}

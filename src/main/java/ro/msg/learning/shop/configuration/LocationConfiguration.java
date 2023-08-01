package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.*;

@Configuration
public class LocationConfiguration {
	@Value("${strategy}")
	StrategyTypeEnum strategy;

	@Bean
	public LocationStrategy locationStrategy() {
		return switch (strategy) {
			case MOST_ABUNDANT -> new MostAbundantLocationImpl();
			case SINGLE -> new SingleLocationImp();
			case GREEDY -> new GreedyLocationImpl();
			default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
		};
	}


}

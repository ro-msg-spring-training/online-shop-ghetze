package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("test")
@Slf4j
public class TestSecurityConfiguration {

	@Bean
	protected SecurityFilterChain configureTestSecurity(HttpSecurity httpSecurity) throws Exception {
		log.info("Test no security.");
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.build();
	}
}

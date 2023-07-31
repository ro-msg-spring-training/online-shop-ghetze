package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@ConditionalOnProperty(value = "shop.authentication", havingValue = "form-based")
@Slf4j
public class FormBasedSecurityConfiguration {

	@Bean
	protected SecurityFilterChain configureWithForm(HttpSecurity httpSecurity) throws Exception {
		log.info("Form based security.");
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/orders/**", "/products/**", "/stocks/**").authenticated()
				.requestMatchers("/", "/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.build();
	}
}

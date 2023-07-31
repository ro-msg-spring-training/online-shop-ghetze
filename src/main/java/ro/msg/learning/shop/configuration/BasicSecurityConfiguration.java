package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(value = "shop.authentication", havingValue = "basic")
public class BasicSecurityConfiguration {

	@Bean
	public SecurityFilterChain defaultFilterChain(HttpSecurity httpSecurity) throws Exception {
			log.info("Basic security.");
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/orders/**", "/products/**", "/stocks/**").authenticated()
				.requestMatchers("/", "/**").permitAll()
				.anyRequest().authenticated()
			)
			.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.httpBasic(Customizer.withDefaults())
			.build();
	}
}

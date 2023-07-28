package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ro.msg.learning.shop.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@Profile("with-basic")
@AllArgsConstructor
public class BasicSecurityConfiguration {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain defaultFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/orders/**", "/products/**", "/stocks/**").authenticated()
				.requestMatchers("/", "/**").permitAll()
				//.anyRequest().authenticated()
			)
			.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.httpBasic(Customizer.withDefaults())
			.build();
	}
}

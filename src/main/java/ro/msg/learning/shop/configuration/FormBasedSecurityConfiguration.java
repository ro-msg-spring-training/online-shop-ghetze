package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ro.msg.learning.shop.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("with-form")
public class FormBasedSecurityConfiguration {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	protected SecurityFilterChain configureWithForm(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/orders/**", "/products/**", "/stocks/**").authenticated()
				.requestMatchers("/", "/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			.build();
	}
}

package ro.msg.learning.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.repositories.CustomerRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private final CustomerRepository customerRepository;

	private static final String USER_NOT_FOUND_MESSAGE = "User name was not found: ";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		final var customer = customerRepository.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException (USER_NOT_FOUND_MESSAGE + username);
		}
		return User
			.withUsername(customer.getUsername())
			.password(customer.getPassword())
			.authorities("ROLE_USER")
			.build();
	}

	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}

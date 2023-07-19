package ro.msg.learning.shop;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ShopApplication {

	public static void main(String[] args) {
		log.info("Shop Online started.");
		SpringApplication.run(ShopApplication.class, args);
	}

	@PreDestroy
	public void onApllicationShutDown()
	{
		log.info("Shop Online stopping.");
	}
}

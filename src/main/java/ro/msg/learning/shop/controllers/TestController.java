package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.interfaces.TestService;

@RestController
@AllArgsConstructor
@Profile("test")
@RequestMapping(value = "/test/database")
public class TestController {
	@Autowired
	private final TestService testService;

	@PostMapping
	public ResponseEntity<String> populateDatabase() {
		testService.populateDatabase();
		return ResponseEntity.status(HttpStatus.OK).body("Database populated successfully.");
	}

	@DeleteMapping
	public ResponseEntity<String> clearDatabase() {
		testService.clearDatabase();
		return ResponseEntity.ok("Database cleared successfully.");
	}

}

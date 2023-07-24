package ro.msg.learning.shop.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor{

	private static final String MESSAGE_EXCEPTION_OCCURRED = "An exception occurred : ";
	private static final String MESSAGE_TIMESTAMP = "Timestamp:";
	private static final String MESSAGE_MSG = "message";
	private static final String MESSAGE_INTERNAL_SERVER_EXCEPTION = "An internal server error occurred.Please contact you administrator.";

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(
		ResourceNotFoundException ex) {

		log.error(MESSAGE_TIMESTAMP + LocalDateTime.now() +  MESSAGE_EXCEPTION_OCCURRED,ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE_MSG, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<Object> handleOrderException(
		OrderException ex) {

		log.error(MESSAGE_TIMESTAMP + LocalDateTime.now() + MESSAGE_EXCEPTION_OCCURRED,ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE_MSG, ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){

		log.error(MESSAGE_TIMESTAMP + LocalDateTime.now() + MESSAGE_EXCEPTION_OCCURRED,ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(MESSAGE_MSG, MESSAGE_INTERNAL_SERVER_EXCEPTION);

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

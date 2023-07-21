package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.services.interfaces.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/orders",produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		Order entity = orderService.createOrder(OrderMapper.toEntity(orderDTO));
		return ResponseEntity.ok(OrderMapper.toDto(entity));
	}

}

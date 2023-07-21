package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entitites.Customer;
import ro.msg.learning.shop.entitites.Order;

@Component
public class OrderMapper {
	public static OrderDTO toDto(Order entity) {
		return new OrderDTO(
			entity.getId(),
			entity.getAddressCity(),
			entity.getAddressCountry(),
			entity.getAddressCounty(),
			entity.getAddressStreet(),
			entity.getCustomer().getId(),
			entity.getCreatedAt(),
			OrderDetailMapper.toListDto(entity.getOrderDetailsList())
		);
	}

	public static Order toEntity(OrderDTO dto) {
		var customer = new Customer();
		customer.setId(dto.getCustomerId());

		return new Order(
			customer,
			dto.getCreatedAt(),
			dto.getAddressCountry(),
			dto.getAddressCity(),
			dto.getAddressCounty(),
			dto.getAddressStreet(),
			OrderDetailMapper.toListEntity(dto.getProductsOrdered())
		);
	}

}

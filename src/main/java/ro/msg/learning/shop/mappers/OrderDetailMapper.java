package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entitites.OrderDetail;
import ro.msg.learning.shop.entitites.Product;

import java.util.List;

@Component
public class OrderDetailMapper {

	public static OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
		Product product = new Product();
		product.setId(orderDetailDTO.getProductId());
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(product);
		orderDetail.setQuantity(orderDetailDTO.getQuantity());
		return orderDetail;
	}

	public static OrderDetailDTO toDto(OrderDetail entity) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setProductId(entity.getProduct().getId());
		orderDetailDTO.setQuantity(entity.getQuantity());
		return orderDetailDTO;
	}

	public static List<OrderDetail> toListEntity(List<OrderDetailDTO> orderDetailListDTO) {
		return orderDetailListDTO.stream().map(dto->toEntity(dto)).toList();
	}

	public static List<OrderDetailDTO> toListDto(List<OrderDetail> orderDetailList) {
		return orderDetailList.stream().map(entity->toDto(entity)).toList();
	}

}

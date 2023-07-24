package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private UUID id;
	private String addressCountry;
	private String addressCity;
	private String addressCounty;
	private String addressStreet;
	private UUID customerId;
	private LocalDateTime createdAt;
	private List<OrderDetailDTO> productsOrdered;

}

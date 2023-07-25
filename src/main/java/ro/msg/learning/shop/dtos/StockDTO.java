package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
	private UUID id;
	private UUID productId;
	private UUID locationId;
	private Integer quantity;
}

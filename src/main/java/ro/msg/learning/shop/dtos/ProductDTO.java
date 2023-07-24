package ro.msg.learning.shop.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private UUID id;
	private String name;
	private String description;
	private BigDecimal price;
	private Double weight;
	private String imageUrl;
	private UUID categoryId;
	private UUID supplierId;
}
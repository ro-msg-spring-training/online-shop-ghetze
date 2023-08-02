package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {

	private UUID id;

	private UUID locationId;

	private BigDecimal sum;
}

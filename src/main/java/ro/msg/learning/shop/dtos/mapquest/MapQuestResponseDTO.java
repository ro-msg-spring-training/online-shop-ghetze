package ro.msg.learning.shop.dtos.mapquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapQuestResponseDTO {
	private List<Long> distance;
	private MapQuestInfoDTO info;
}

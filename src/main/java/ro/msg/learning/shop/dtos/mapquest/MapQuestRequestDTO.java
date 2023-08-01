package ro.msg.learning.shop.dtos.mapquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dtos.AddressDTO;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapQuestRequestDTO {
	private List<AddressDTO> locations;
}

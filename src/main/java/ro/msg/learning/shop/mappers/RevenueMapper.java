package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.RevenueDTO;
import ro.msg.learning.shop.entitites.Revenue;

import java.util.List;

@Component
public class RevenueMapper {
	public static RevenueDTO tooDto(Revenue revenue) {
		RevenueDTO revenueDTO = new RevenueDTO();
		revenueDTO.setId(revenue.getId());
		revenueDTO.setLocationId(revenue.getLocation().getId());
		revenueDTO.setSum(revenue.getSum());
		return revenueDTO;
	}

	public static List<RevenueDTO> toDtoList(List<Revenue> revenueList) {
		return revenueList.stream().map(entity->tooDto(entity)).toList();
	}
}

package ro.msg.learning.shop.dtos.mapquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapQuestInfoDTO {
	@JsonProperty("statuscode")
	private Integer statusCode;
	private List<String> messages;
}

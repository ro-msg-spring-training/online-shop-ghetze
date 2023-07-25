package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.StockDTO;
import ro.msg.learning.shop.entitites.Location;
import ro.msg.learning.shop.entitites.Product;
import ro.msg.learning.shop.entitites.Stock;

import java.util.List;

@Component
public class StockMapper {
	public static StockDTO tooDto(Stock stock) {
		StockDTO stockDTO = new StockDTO();
		stockDTO.setId(stock.getId());
		stockDTO.setProductId(stock.getProduct().getId());
		stockDTO.setLocationId(stock.getLocation().getId());
		stockDTO.setQuantity(stock.getQuantity());
		return stockDTO;
	}

	public static Stock toEntity(StockDTO stockDTO) {
		Stock stock = new Stock();
		stock.setId(stockDTO.getId());

		Product product = new Product();
		product.setId(stockDTO.getProductId());
		stock.setProduct(product);

		Location location = new Location();
		location.setId(stockDTO.getLocationId());
		stock.setLocation(location);

		stock.setQuantity(stockDTO.getQuantity());
		return stock;
	}

	public static List<StockDTO> toDtoList(List<Stock> stockList) {
		return stockList.stream().map(entity->tooDto(entity)).toList();
	}

	public static List<Stock> toEntityList(List<StockDTO> stockDTOList) {
		return stockDTOList.stream().map(dto->toEntity(dto)).toList();
	}
}

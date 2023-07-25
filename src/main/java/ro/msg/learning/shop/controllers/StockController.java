package ro.msg.learning.shop.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.StockDTO;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.mappers.StockMapper;
import ro.msg.learning.shop.services.interfaces.StockService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/stocks")
public class StockController {
	private final StockService stockService;

	@GetMapping(value = "/{locationId}", produces = {"text/csv"})
	public ResponseEntity<List<StockDTO>> exportStocksByLocationId(@PathVariable UUID locationId, HttpServletResponse response) {

		List<Stock> stocks = stockService.getStocksForLocationId(locationId);
		List<StockDTO> stockDTOList = StockMapper.toDtoList(stocks);
		response.setHeader("Content-Disposition", "attachment; filename=stocks_for_locationId_"+locationId+".csv");

		return ResponseEntity.ok(stockDTOList);
	}
}

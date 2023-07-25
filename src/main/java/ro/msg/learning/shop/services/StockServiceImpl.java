package ro.msg.learning.shop.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.services.interfaces.StockService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
	@Autowired
	private final StockRepository stockRepository;
	@Override
	public List<Stock> getStocksForLocationId(UUID locationId) {
		return stockRepository.findByLocationId(locationId);
	}
}

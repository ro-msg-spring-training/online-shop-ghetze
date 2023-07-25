package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.entitites.Stock;

import java.util.List;
import java.util.UUID;

public interface StockService {

	List<Stock> getStocksForLocationId(UUID locationId);
}


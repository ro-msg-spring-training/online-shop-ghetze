package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dtos.AddressDTO;
import ro.msg.learning.shop.dtos.mapquest.MapQuestRequestDTO;
import ro.msg.learning.shop.dtos.mapquest.MapQuestResponseDTO;
import ro.msg.learning.shop.entitites.Order;
import ro.msg.learning.shop.entitites.Stock;
import ro.msg.learning.shop.exceptions.MapQuestException;
import ro.msg.learning.shop.exceptions.OrderException;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class GreedyLocationImpl implements LocationStrategy{

	@Value("${mapquest.api.key}")
	private String mapQuestApiKey;

	@Value("${mapquest.api.url}")
	private String mapQuestApiUrl;
	@Autowired
	private StockRepository stockRepository;
	private static final String MESSAGE_PRODUCTS_NO_STOCKS = "There are no sufficient stocks for your products";
	private static final String MESSAGE_MAP_QUEST_ERROR = "There was an error when calling MapQuest api.";
	private static final String MESSAGE_MAP_QUEST_WRONG_DISTANCE_NO = " locations expected, but MapQuest api returned: ";
	private static final String MESSAGE_MAP_QUEST_NO_RESPONSE = "There is no response from MapQuest api.";
	@Override
	public List<Stock> getAvailableStocks(Order order) {
		List<Stock> stocksWithAllProducts = new ArrayList<>();

		order.getOrderDetailsList().forEach(orderDetail -> {
				var productId = orderDetail.getProduct().getId();

				//get a list with all locations with sufficient stock for a product.
				List<Stock> productStocks = stockRepository.findStockByProductAndQuantity(productId, orderDetail.getQuantity());

				//if there are no stocks throw exception
				if (productStocks.isEmpty())
				{
					throw new OrderException(MESSAGE_PRODUCTS_NO_STOCKS);
				}

				Stock closestLocationStock = findStockWithClosestLocation(productStocks,order);

				stocksWithAllProducts.add(closestLocationStock);
			}
		);

		return stocksWithAllProducts;
	}

	private Stock findStockWithClosestLocation(List<Stock> stocks, Order order) {

		//set the originating location address
		AddressDTO originLocationAddress = new AddressDTO(order.getAddressStreet(), order.getAddressCity(), order.getAddressCounty(), order.getAddressCountry());

		// Prepare the request body containing the data for the API call
		MapQuestRequestDTO requestEntity = new MapQuestRequestDTO(buildMapQuestLocations(stocks, originLocationAddress));


		MapQuestResponseDTO response = getMapQuestResponse(requestEntity);

		List<Long> distances = response.getDistance();
		//remove the first item as we don't need it as it is the order location.
		distances.remove(0);

		//get the shortest Distance
		var shortestDistance = distances.stream().min(Comparator.naturalOrder()).orElseThrow();

		//get the shortest distance index
		int closestLocationIndex = distances.indexOf(shortestDistance);

		// Return the stock with the closest location
		return stocks.get(closestLocationIndex);
	}

	private MapQuestResponseDTO getMapQuestResponse(MapQuestRequestDTO requestEntity) {

		RestTemplate restTemplate = new RestTemplate();
		// Make the API call to MapQuest route matrix API using POST and get the response
		String destinationUrl = mapQuestApiUrl + "?key=" + mapQuestApiKey;
		MapQuestResponseDTO response;
		try {
			response = restTemplate.postForObject(destinationUrl, requestEntity, MapQuestResponseDTO.class);
		} catch (Exception ex) {
			throw new MapQuestException(MESSAGE_MAP_QUEST_ERROR + destinationUrl + ex.getMessage());
		}

		if (response == null || response.getInfo() == null)
		{
			throw new MapQuestException(MESSAGE_MAP_QUEST_NO_RESPONSE);
		}

		if (response.getDistance().size() != requestEntity.getLocations().size())
		{
			throw new MapQuestException(requestEntity.getLocations().size() + MESSAGE_MAP_QUEST_WRONG_DISTANCE_NO + response.getDistance().size());
		}

		if(response.getInfo().getStatusCode() != 0)
		{
			throw new MapQuestException(String.join("/n",response.getInfo().getMessages()));
		}

		return response;
	}

	// creates the list of locations that will pbe passed to the MapQUEST api.
	private List<AddressDTO> buildMapQuestLocations(List<Stock> stocksAddresses, AddressDTO originLocationAddress) {
		List<AddressDTO> locationAddressesList = stocksAddresses.stream()
			.map(stock -> new AddressDTO(
				stock.getLocation().getAddressStreet(),
				stock.getLocation().getAddressCity(),
				stock.getLocation().getAddressCounty(),
				stock.getLocation().getAddressCountry()
			))
			.collect(Collectors.toList());
		//add the order address as the first location from the list
		locationAddressesList.add(0, originLocationAddress);

		return locationAddressesList;
	}
}

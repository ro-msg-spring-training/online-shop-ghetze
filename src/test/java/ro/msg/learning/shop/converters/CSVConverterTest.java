package ro.msg.learning.shop.converters;

import org.junit.jupiter.api.Test;
import ro.msg.learning.shop.dtos.StockDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVConverterTest {

	@Test
	public void testCsvSerialization() throws Exception {
		List<StockDTO> stocks = Arrays.asList(
			new StockDTO(
				UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1231"),
				UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"),
				UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231"),
				10),
			new StockDTO(
				UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1232"),
				UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"),
				UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1232"),
				5)
		);

		// Serialize to CSV
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		CSVConverterUtils.toCsv(StockDTO.class, stocks, outputStream);
		String csvData = outputStream.toString();

		// Assertions
		String expectedCsvData = """
				id,locationId,productId,quantity
				"531e4cdd-bb78-4769-a0c7-cb948a9f1231","431e4cdd-bb78-4769-a0c7-cb948a9f1231","331e4cdd-bb78-4769-a0c7-cb948a9f1231",10
				"531e4cdd-bb78-4769-a0c7-cb948a9f1232","431e4cdd-bb78-4769-a0c7-cb948a9f1232","331e4cdd-bb78-4769-a0c7-cb948a9f1231",5
				""";

		assertEquals(expectedCsvData, csvData);
	}

	@Test
	public  void testCsvDeserialization() throws Exception {
		// Sample CSV data
		String csvData = """
				id,locationId,productId,quantity
				"531e4cdd-bb78-4769-a0c7-cb948a9f1231","431e4cdd-bb78-4769-a0c7-cb948a9f1231","331e4cdd-bb78-4769-a0c7-cb948a9f1231",10
				""";

		// Deserialize from CSV
		ByteArrayInputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
		List<StockDTO> deserializedStocks = CSVConverterUtils.fromCsv(StockDTO.class, inputStream);

		// Assertions
		assertEquals(1, deserializedStocks.size());
		assertEquals(UUID.fromString("531e4cdd-bb78-4769-a0c7-cb948a9f1231"), deserializedStocks.get(0).getId());
		assertEquals(UUID.fromString("431e4cdd-bb78-4769-a0c7-cb948a9f1231"), deserializedStocks.get(0).getLocationId());
		assertEquals(UUID.fromString("331e4cdd-bb78-4769-a0c7-cb948a9f1231"), deserializedStocks.get(0).getProductId());
		assertEquals(10, deserializedStocks.get(0).getQuantity());
	}
}

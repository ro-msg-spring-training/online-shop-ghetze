package ro.msg.learning.shop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.utils.TestingUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = ShopApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties", properties = {"location.strategy=most_abundant"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderForMostAbundantLocationIntegrationTest {
	@Autowired
	public MockMvc mockMvc;
	@Autowired
	public ObjectMapper mapper;

	private static final String MESSAGE_PRODUCTS_NO_STOCKS = "There are no sufficient stocks for your products";

	@BeforeAll
	public void setUpDatabase() throws Exception {
		mockMvc.perform(post("/test/database")).andExpect(status().isOk());
	}

	@AfterAll
	public  void clearDatabase () throws Exception {
		mockMvc.perform(delete("/test/database")).andExpect(status().isOk());
	}

	@Test
	void createOrder_thenStatus200() throws Exception {

		var ow = mapper.writer().withDefaultPrettyPrinter();
		var jsonRequestBody = ow.writeValueAsString(TestingUtils.orderDTOWith2productsAndQuantity1);

		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestBody))
			.andExpect(status().isOk());
	}

	@Test
	void createOrder_failedDueToMissingStock() throws Exception {

		var ow = mapper.writer().withDefaultPrettyPrinter();
		var jsonRequestBody = ow.writeValueAsString(TestingUtils.orderDTOWith2productsAndQuantityBiggerThenStocks);

		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestBody))
			.andExpect(MockMvcResultMatchers.status().isInternalServerError())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(MESSAGE_PRODUCTS_NO_STOCKS));
	}

}

package ro.msg.learning.shop.controllers;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = ShopApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties", properties = {"location.strategy=most_abundant"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MostAbundantLocationOrderControllerIntegrationTest {
	@Autowired
	public MockMvc mockMvc;

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
		String jsonRequestBody = """
			{
			  "id":"777e4cdd-bb78-4769-a0c7-cb948a9f1231",
			  "productsOrdered": [
			    {
			      "productId": "331e4cdd-bb78-4769-a0c7-cb948a9f1231",
			      "quantity": 1
			    },
			    {
			      "productId": "331e4cdd-bb78-4769-a0c7-cb948a9f1232",
			      "quantity": 1
			    }
			  ],
			  
			  "createdAt": "2023-07-11T10:30:00",
			  "addressCountry": "Romania",
			  "addressCity": "Floresti",
			  "addressStreet": "Sub cetate",
			  "addressCounty": "Cluj",
			  "customerId": "631e4cdd-bb78-4769-a0c7-cb948a9f1231"
			}""";

		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestBody))
			.andExpect(status().isOk());
	}

	@Test
	void createOrder_failedDueToMissingStock() throws Exception {
		String jsonRequestBody = """
			{
			  "id":"777e4cdd-bb78-4769-a0c7-cb948a9f1231",
			  "productsOrdered": [
			    {
			      "productId": "331e4cdd-bb78-4769-a0c7-cb948a9f1231",
			      "quantity": 1
			    },
			    {
			      "productId": "331e4cdd-bb78-4769-a0c7-cb948a9f1232",
			      "quantity": 100
			    }
			  ],
			  "createdAt": "2023-07-11T10:30:00",
			   "addressCountry": "Romania",
			   "addressCity": "Floresti",
			   "addressStreet": "Sub cetate",
			   "addressCounty": "Cluj"
			  },
			  "customerId": "631e4cdd-bb78-4769-a0c7-cb948a9f1231"
			}""";

		mockMvc.perform(MockMvcRequestBuilders.post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequestBody))
			.andExpect(MockMvcResultMatchers.status().isInternalServerError())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(MESSAGE_PRODUCTS_NO_STOCKS));
	}

}

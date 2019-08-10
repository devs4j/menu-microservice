package mx.com.devs4j.microservices.menu;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MenuItemCT {

	@Autowired
	private MockMvc mockMvc;

	private MockRestServiceServer mockServer;

	@Autowired
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws IOException {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		
		mockServer.expect(MockRestRequestMatchers.requestTo("http://mymockapi/latest?base=MXN&symbols=USD"))
			.andRespond(MockRestResponseCreators.withSuccess(
				TestUtils.readFileAsString("exchangeRate.json"), MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldGetAllItems() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/items"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void shouldCreateItem() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/items").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.readFileAsString("items_post.json")));

		mockMvc.perform(MockMvcRequestBuilders.get("/items")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(4)));
	}

}

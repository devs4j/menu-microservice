package mx.com.devs4j.microservices.menu;

import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MenuItemCT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldGetAllItems() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/items"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldCreateItem() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.readFileAsString("items_post.json")));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/items"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content()
					.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(4)));
	}

}

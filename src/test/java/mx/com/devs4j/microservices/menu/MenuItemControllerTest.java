package mx.com.devs4j.microservices.menu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MenuItemService menuItemService;

	@Test
	public void shouldGetAllMenuItems() throws Exception {
		Mockito.when(menuItemService.getAll()).thenReturn(createMenuItems());

		mockMvc.perform(MockMvcRequestBuilders.get("/items"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(
				MockMvcResultMatchers.content()
				.json(TestUtils.readFileAsString("menuItems_getAll.json")));
	}

	public List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(createMenuItem());
		return menuItems;
	}

	private MenuItem createMenuItem() {
		MenuItem mi = new MenuItem();
		mi.setId(1);
		mi.setName("Cafe Americano");
		mi.setDescription("Descripcion");
		mi.setPrice(Double.valueOf(20.0));
		mi.setPriceInUsd(Double.valueOf(1.0));
		return mi;
	}

}

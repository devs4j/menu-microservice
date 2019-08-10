package mx.com.devs4j.microservices.menu;

import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;

@Provider("menuItem")
@Consumer("order")
@PactFolder("src/test/resources/pacts")
@RunWith(SpringRestPactRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MenuItemController.class)
public class MenuItemControllerForOrderAT {

	@TestTarget
	public final SpringBootHttpTarget target = new SpringBootHttpTarget();
	
	@MockBean
	private MenuItemService menuItemService;
	
	@State("get one menu item1")
	public void shouldReturnOneItem1() {
		Optional<MenuItem> menuItem = createMenuItem1();
		Mockito.when(menuItemService.findById(Matchers.eq(1))).thenReturn(menuItem);
	}
	
	@State("get one menu item2")
	public void shouldReturnOneItem2() {
		Optional<MenuItem> menuItem = createMenuItem2();
		Mockito.when(menuItemService.findById(Matchers.eq(2))).thenReturn(menuItem);
	}
	
	public Optional<MenuItem> createMenuItem2() {
		MenuItem menuItem = new MenuItem();
		menuItem.setId(2);
		menuItem.setName("Cafe Americano");
		menuItem.setDescription("Descripcion");
		menuItem.setPrice(Double.valueOf(20.0));
		menuItem.setPriceInUsd(Double.valueOf(1));
		
		return Optional.of(menuItem);
	}
	
	public Optional<MenuItem> createMenuItem1() {
		MenuItem menuItem = new MenuItem();
		menuItem.setId(1);
		menuItem.setName("Cafe Americano");
		menuItem.setDescription("Descripcion");
		menuItem.setPrice(Double.valueOf(20.0));
		menuItem.setPriceInUsd(Double.valueOf(1));
		
		return Optional.of(menuItem);
	}

}

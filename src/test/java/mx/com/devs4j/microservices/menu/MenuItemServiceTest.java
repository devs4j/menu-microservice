package mx.com.devs4j.microservices.menu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import mx.com.devs4j.microservices.menu.exchange.ExchangeRateGateway;

public class MenuItemServiceTest {
	
	private MenuItemRepository menuItemRepository;
	private ExchangeRateGateway exchangeRateGateway;
	
	private MenuItemService menuItemService;
	
	@Before
	public void before() {
		menuItemRepository = Mockito.mock(MenuItemRepository.class);
		exchangeRateGateway = Mockito.mock(ExchangeRateGateway.class);
		
		menuItemService = new MenuItemService(menuItemRepository, exchangeRateGateway);
	}
	
	@Test
	public void shouldGetAllMenuItems() {
		MenuItem mi = new MenuItem();
		mi.setName("Cafe Americano");
		mi.setDescription("Descripcion");
		mi.setPrice(Double.valueOf(20.0));
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(mi);
		
		Mockito.when(exchangeRateGateway.getExchangeRateInUsd()).thenReturn(Double.valueOf(0.05));
		Mockito.when(menuItemRepository.findAll()).thenReturn(menuItems);
		
		List<MenuItem> allItems = menuItemService.getAll();
		
		Assert.assertEquals(1, allItems.size());
		MenuItem menuItem = allItems.get(0);
		Assert.assertEquals("Cafe Americano", menuItem.getName());
		Assert.assertEquals("Descripcion", menuItem.getDescription());
		Assert.assertEquals(Double.valueOf(20.0), menuItem.getPrice());
		Assert.assertEquals(Double.valueOf(1.0), menuItem.getPriceInUsd());
	}

}

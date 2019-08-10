package mx.com.devs4j.microservices.menu;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
public class MenuItemRepositoryIT {
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Test
	public void shouldSaveMenuItem() {
		MenuItem newMenuItem = createMenuItem();
		
		menuItemRepository.save(newMenuItem);
		
		List<MenuItem> menuItems = menuItemRepository.findAll();
		Assert.assertEquals(1, menuItems.size());
	}
	
	private MenuItem createMenuItem() {
		MenuItem mi = new MenuItem();
		mi.setName("Cafe Americano");
		mi.setDescription("Descripcion");
		mi.setPrice(Double.valueOf(20.0));
		return mi;
	}

}

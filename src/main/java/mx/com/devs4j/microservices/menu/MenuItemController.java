package mx.com.devs4j.microservices.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController {
	
	private MenuItemService menuItemService;
	private String helloStr;
	
	public MenuItemController(MenuItemService menuItemService, @Value("${property}") String helloStr) {
		this.menuItemService = menuItemService;
		this.helloStr = helloStr;
	}
	
	@GetMapping("/hello") 
	public String hello() {
		return helloStr;
	}

	@GetMapping("/items")
	public List<MenuItem> getAllMenuItems() {
		return menuItemService.getAll();
	}
	
	@PostMapping("/items")
	public MenuItem newMenuItem(@RequestBody MenuItem menuItem) {
		return menuItemService.save(menuItem);
	}

	@GetMapping("/items/{id}")
	public MenuItem one(@PathVariable Integer id) {
		return menuItemService.findById(id).orElseThrow(() -> new RuntimeException("Id not found" + id));
	}

	@PutMapping("/items/{id}")
	public MenuItem replaceMenuItem(@RequestBody MenuItem newMenuItem, @PathVariable Integer id) {
		return menuItemService.findById(id).map(menuItem -> {
			menuItem.setName(menuItem.getName());
			menuItem.setDescription(menuItem.getDescription());
			return menuItemService.save(menuItem);
		}).orElseGet(() -> {
			newMenuItem.setId(id);
			return menuItemService.save(newMenuItem);
		});
	}

	@DeleteMapping("/items/{id}")
	public void deleteMenuItem(@PathVariable Integer id) {
		menuItemService.deleteById(id);
	}
	
}

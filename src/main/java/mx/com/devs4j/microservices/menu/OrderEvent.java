package mx.com.devs4j.microservices.menu;
import java.util.List;

public class OrderEvent {
	
	private Integer id;
	private List<Integer> menuItemsIds;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getMenuItemsIds() {
		return menuItemsIds;
	}
	public void setMenuItemsIds(List<Integer> menuItemsIds) {
		this.menuItemsIds = menuItemsIds;
	}
	
}

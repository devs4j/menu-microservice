package mx.com.devs4j.microservices.menu;

import java.util.LinkedHashSet;
import java.util.Set;

public class Order {

	private Long id;
	private Set<MenuItem> items = new LinkedHashSet<>();
	
	public Order(Set<MenuItem> items) {
		super();
		this.items = items;
	}
	
	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<MenuItem> getItems() {
		return items;
	}

	public void setItems(Set<MenuItem> items) {
		this.items = items;
	}

}
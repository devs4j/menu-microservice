package mx.com.devs4j.microservices.menu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "menu_item")
@Entity
public class MenuItem {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer inventory;
	private transient Double priceInUsd;
	
	public MenuItem() {}
	
	public MenuItem(String name, String description, Double price, Integer inventory) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
	}
	
	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Double getPriceInUsd() {
		return priceInUsd;
	}

	public void setPriceInUsd(Double priceInUsd) {
		this.priceInUsd = priceInUsd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

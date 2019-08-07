package mx.com.devs4j.microservices.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.com.devs4j.microservices.menu.exchange.ExchangeRateGateway;

@Service
public class MenuItemService {
	
	private MenuItemRepository menuItemRepository;
	private ExchangeRateGateway exchangeRateGateway;
	
	public MenuItemService(MenuItemRepository menuItemRepository, 
			ExchangeRateGateway exchangeRateGateway) {
		this.menuItemRepository = menuItemRepository;
		this.exchangeRateGateway = exchangeRateGateway;
	}
	
	public List<MenuItem> getAll() {
		double exchangeRateInUsd = exchangeRateGateway.getExchangeRateInUsd();
		List<MenuItem> menuItems = menuItemRepository.findAll();
		for (MenuItem menuItem : menuItems) {
			menuItem.setPriceInUsd(menuItem.getPrice() * exchangeRateInUsd);
		}
		return menuItems;
	}

	public void deleteById(Integer id) {
		menuItemRepository.deleteById(id);
	}

	public Optional<MenuItem> findById(Integer id) {
		double exchangeRateInUsd = exchangeRateGateway.getExchangeRateInUsd();
		Optional<MenuItem> menuItemOpt = menuItemRepository.findById(id);
		if (menuItemOpt.isPresent()) {
			MenuItem menuItem = menuItemOpt.get();
			menuItem.setPriceInUsd(menuItem.getPrice() * exchangeRateInUsd);
		}
		return menuItemOpt;
	}

	public MenuItem save(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}
}

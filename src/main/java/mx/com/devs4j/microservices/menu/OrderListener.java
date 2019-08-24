package mx.com.devs4j.microservices.menu;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

	private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);

	private MenuItemService menuItemService;
	private CustomChannels customChannels;

	public OrderListener(MenuItemService menuItemService, CustomChannels customChannels) {
		this.menuItemService = menuItemService;
		this.customChannels = customChannels;
	}

	@StreamListener("order-created")
	public void orderCreated(OrderEvent orderEvent) {
		logger.info("ORDER RECEIVED {}", orderEvent.getId());
		boolean inventoryAvailable = true;

		List<MenuItem> menuItems = orderEvent.getMenuItemsIds().stream().map((id) -> menuItemService.findById(id).get())
				.collect(Collectors.toList());

		for (MenuItem menuItem : menuItems) {
			if (menuItem.getInventory() < 1) {
				logger.info("NO INVENTORY FOR ITEM {}", menuItem.getId());
				inventoryAvailable = false;
			} else {
				menuItem.setInventory(menuItem.getInventory() - 1);
			}
		}

		if (inventoryAvailable) {
			menuItems.forEach((mi) -> {
				menuItemService.save(mi);
			});
			logger.info("INVENTORY AVAILABLE {}", orderEvent.getId());
			customChannels.inventoryAvailable().send(MessageBuilder.withPayload(orderEvent).build());
		} else {
			logger.info("NO INVENTORY {}", orderEvent.getId());
			customChannels.noInventory().send(MessageBuilder.withPayload(orderEvent).build());
		}

	}

}

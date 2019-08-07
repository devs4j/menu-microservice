package mx.com.devs4j.microservices.menu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.com.devs4j.microservices.menu.MenuItem;
import mx.com.devs4j.microservices.menu.MenuItemRepository;

@Configuration
class LoadDatabase {
	
	private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(MenuItemRepository repository) {
		MenuItem menuItem1 = new MenuItem("Espresso", "Delicioso cafe espresso", 10.45);
		MenuItem menuItem2 = new MenuItem("Americano", "Café orgánico de chiapas", 13.00);
		MenuItem menuItem3 = new MenuItem("Capuccino", "Delicioso cafe con una capa de espuma", 18.90);
		return args -> {
			log.info("Preloading " + repository.save(menuItem1));
			log.info("Preloading " + repository.save(menuItem2));
			log.info("Preloading " + repository.save(menuItem3));
		};
	}
}
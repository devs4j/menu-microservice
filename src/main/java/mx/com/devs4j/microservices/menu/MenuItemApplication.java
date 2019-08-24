package mx.com.devs4j.microservices.menu;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import mx.com.devs4j.microservices.menu.infrastructure.CustomChannels;
import mx.com.devs4j.microservices.menu.order.Order;

@EnableBinding(CustomChannels.class)
@SpringBootApplication
public class MenuItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuItemApplication.class, args);
	}

	@StreamListener("order-created")
	public void orderListener(Order newOrder) {
		LoggerFactory.getLogger(this.getClass()).info("Order received {}", newOrder);
	}

}

package mx.com.devs4j.microservices.menu.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
	
	@Input("order-created")
	SubscribableChannel orderCreated();
	
}
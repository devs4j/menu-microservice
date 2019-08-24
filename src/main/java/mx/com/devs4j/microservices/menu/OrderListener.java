package mx.com.devs4j.microservices.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
	
	@StreamListener("order-created")
	public void listen(Order newOrder) {
		logger.info("ORDER RECEIVED {}", newOrder.getId());
	}

}

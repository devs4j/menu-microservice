package mx.com.devs4j.microservices.menu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer 
@SpringBootApplication
public class MenuItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuItemApplication.class, args);
	}

}

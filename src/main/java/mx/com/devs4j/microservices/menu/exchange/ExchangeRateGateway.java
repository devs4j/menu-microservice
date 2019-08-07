package mx.com.devs4j.microservices.menu.exchange;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeRateGateway {
	
	private RestTemplate restTemplate;
	private String url;

	public ExchangeRateGateway(RestTemplate restTemplate, @Value("${exchangeRateUrl}") String url) {
		super();
		this.restTemplate = restTemplate;
		this.url = url;
	}
	
	public double getExchangeRateInUsd() {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("base", "MXN");
		uriVariables.put("symbols", "USD");
		ExchangeRateResult exchangeRateResult = restTemplate.getForObject(url, ExchangeRateResult.class, uriVariables);
		return exchangeRateResult.getRates().getUsd();
	}

}

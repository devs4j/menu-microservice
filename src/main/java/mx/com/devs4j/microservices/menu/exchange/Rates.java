package mx.com.devs4j.microservices.menu.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
	
	@JsonProperty("USD")
	private double usd;

	public double getUsd() {
		return usd;
	}

	public void setUsd(double usd) {
		this.usd = usd;
	}
	
}

package mx.com.devs4j.microservices.menu.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateResult {

	@JsonProperty("rates")
	private Rates rates;
	@JsonProperty("base")
	private String base;
	@JsonProperty("date")
	private String date;

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}

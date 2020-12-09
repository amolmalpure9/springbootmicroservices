package com.demo.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.currencyconversionservice.bean.ConversionBean;
import com.demo.currencyconversionservice.feignproxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "/exchange_old/from/{fcurr}/to/{tcurr}/amount/{qty}")
	public ConversionBean getConversionRate(@PathVariable String fcurr, @PathVariable String tcurr,
			@PathVariable BigDecimal qty) {

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", fcurr);
		uriVariables.put("to", tcurr);
		uriVariables.put("qty", qty.toString());
		ResponseEntity<ConversionBean> response = new RestTemplate()
				.getForEntity("http://localhost:8002/exchange/from/{from}/to/{to}", ConversionBean.class, uriVariables);
		ConversionBean resbean = response.getBody();
		return new ConversionBean(resbean.getId(), resbean.getFrom(), resbean.getTo(),
				resbean.getConversionMultiplier(), qty, qty.multiply(resbean.getConversionMultiplier()),
				resbean.getPort());

	}

	@GetMapping(path = "/exchange/from/{fcurr}/to/{tcurr}/amount/{qty}")
	public ConversionBean getConversionRateFeign(@PathVariable String fcurr, @PathVariable String tcurr,
			@PathVariable BigDecimal qty) {
		logger.info(
				"--- In Method : /exchange/from/{fcurr}/to/{tcurr}/amount/{qty} => " + fcurr + " " + tcurr + " " + qty);
		ConversionBean resbean = currencyExchangeServiceProxy.retrieveExchangeValue(fcurr, tcurr);
		return new ConversionBean(resbean.getId(), resbean.getFrom(), resbean.getTo(),
				resbean.getConversionMultiplier(), qty, qty.multiply(resbean.getConversionMultiplier()),
				resbean.getPort());

	}
}

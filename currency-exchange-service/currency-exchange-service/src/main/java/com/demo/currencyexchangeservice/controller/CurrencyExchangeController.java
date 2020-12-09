package com.demo.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.currencyexchangeservice.bean.ExchangeRate;
import com.demo.currencyexchangeservice.dao.ExchangeRepository;

@RestController
public class CurrencyExchangeController {

	/**
	 * This is spring provided 
	 */
	@Autowired
	private Environment env;

	@Autowired
	private ExchangeRepository exchangeRepository;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(path = "/exchange/from/{fcurr}/to/{tcurr}")
	public ResponseEntity<ExchangeRate> getConversionRate(@PathVariable String fcurr, @PathVariable String tcurr) {
		logger.info("--- In Method : /exchange/from/{fcurr}/to/{tcurr} => "+fcurr+" "+tcurr);
//		ExchangeRate e = new ExchangeRate(1l, "INR", "USD", new BigDecimal(650));
//		e.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		ExchangeRate exchangeRate = exchangeRepository.findByFromAndTo(fcurr, tcurr);
		if(null != exchangeRate)
		exchangeRate.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return new ResponseEntity<ExchangeRate>(exchangeRate, HttpStatus.ACCEPTED);
	}
}

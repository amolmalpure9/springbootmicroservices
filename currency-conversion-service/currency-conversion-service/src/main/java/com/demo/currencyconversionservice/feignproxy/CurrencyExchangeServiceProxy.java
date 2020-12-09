package com.demo.currencyconversionservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.currencyconversionservice.bean.ConversionBean;

//@FeignClient(name = "currency-exchange-service")
//@RibbonClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
//	@GetMapping(path = "/exchange/from/{fcurr}/to/{tcurr}")
	@GetMapping(path = "currency-exchange-service/exchange/from/{fcurr}/to/{tcurr}")
	public ConversionBean retrieveExchangeValue(@PathVariable("fcurr") String fcurr, @PathVariable("tcurr") String tcurr);
}

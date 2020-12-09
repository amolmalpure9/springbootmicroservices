package com.demo.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitServiceConfigurationController {

	@Autowired
	private AppConfig appConfig;

	@GetMapping(path = "/getConfiguration")
	public LimitConfigruation retrieveConfig() {
		return new LimitConfigruation(appConfig.getMax(), appConfig.getMin());
	}
}

package com.spring_inc.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "commander-service")
public interface CommanderClient {

	@GetMapping("/commander/test")
	public String getTest();

}

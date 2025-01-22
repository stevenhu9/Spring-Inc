package com.spring_inc.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "commander-service")
public interface CommanderClient {

	@GetMapping("/pilot/commander/test")
	public String getTest();
	
	@PostMapping("/pilot/commander")
	public String getAssociatedSquadron(Integer squadron);
	
}

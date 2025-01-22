package com.spring_inc.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "squadron-service")
public interface SquadronClient {
	@GetMapping("/squadron/test")
	public String getTest();
//	@PostMapping("/pilot/squadron")
//	public String getAssociatedSquadron(Integer squadron);
}

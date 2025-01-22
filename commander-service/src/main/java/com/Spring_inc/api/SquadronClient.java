package com.Spring_inc.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "squadron-service")
public interface SquadronClient {
	
	@GetMapping("/squadron/commander")
	public String getAssociatedSquadron(Integer squadronID);
}

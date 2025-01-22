package com.spring_inc.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "squadron-service")
public interface SquadronClient {
	@PostMapping("/squadron/commander")
	public String getAssociatedCommander(Integer commander);
}

package com.spring_inc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/squadron")
public class SquadronController {
	
	@GetMapping
	public String test() {
		return "works";
	}
}

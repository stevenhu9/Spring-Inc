package com.Spring_inc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commander")
public class CommanderController {
	@GetMapping
	public String test() {
		return "works";
	}
}

package com.Spring_inc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring_inc.api.SquadronClient;
import com.Spring_inc.dtos.CommanderDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.services.CommanderService;

@RestController
@RequestMapping("/commander")
public class CommanderController {
	
	private SquadronClient client;
	private CommanderService service;
	
	public CommanderController(CommanderService commanderservice,SquadronClient squadronclient) {
		this.client = squadronclient;
		this.service = commanderservice;
	}
	
	// find all
	@GetMapping
	public ResponseEntity<Iterable<Commander>> findAll() {
		return service.findAll();
	}
	
	// get commander
	@GetMapping("/test")
	public String test() {
		return "this message came from commander controller";
	}
			/*
	// find one by ID
	@GetMapping("/{CommanderId}")
	public ResponseEntity<Commander> findById(@PathVariable int CommanderId) {
		return service.findById(CommanderId);
	}
			
	// add one
	@PostMapping
	public ResponseEntity<Commander> addOne(@RequestBody CommanderDTO CommanderDTO) {
		return service.addOne(CommanderDTO);
	}
			
	// update one
	@PutMapping("/{CommanderId}")
	public ResponseEntity<Commander> updateOne(@PathVariable int CommanderId, @RequestBody CommanderDTO CommanderDTO) {
		return service.updateOne(CommanderId, CommanderDTO);
	}
			
	// delete one
	@DeleteMapping("/{CommanderId}")
	public ResponseEntity<Void> deleteOne(@PathVariable int CommanderId) {
		return service.deleteOne(CommanderId);
	}*/
}

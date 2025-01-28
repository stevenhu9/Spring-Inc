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
import com.Spring_inc.dtos.ResponseDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.services.CommanderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/commander")
public class CommanderController {
	
	private SquadronClient client;
	private CommanderService service;
	
	public CommanderController(CommanderService commanderService,SquadronClient squadronClient) {
		this.client = squadronClient;
		this.service = commanderService;
	}
	
	// find all
	@GetMapping
	@Operation(summary ="Get All Commanders", description = "Fetches all Commanders from the database")
	public ResponseEntity<Iterable<Commander>> findAll() {
		return service.findAll();
	}
		
	// find one by ID
	@GetMapping("/{commanderId}")
	@Operation(summary ="Find one Commander by Id", description = "Searches the database and finds a Commander based on the given Id")
	public ResponseEntity<Commander> findById(@PathVariable int commanderId) {
		return service.findById(commanderId);
	}
			
	// add one
	@PostMapping
	@Operation(summary ="Add Commander", description = "Adds a Commander to the database")
	public ResponseEntity<Commander> addOne(@RequestBody CommanderDTO commanderDTO) {
		return service.addOne(commanderDTO);
	}
			
	// update one
	@PutMapping("/{commanderId}")
	@Operation(summary ="Update Commander", description = "Finds a Commander in the database and updates it with the given information")
	public ResponseEntity<Commander> updateOne(@PathVariable int commanderId, @RequestBody CommanderDTO commanderDTO) {
		return service.updateOne(commanderId, commanderDTO);
	}
			
	// delete a commander with no squad/pilots assigned to it
	@DeleteMapping("/{commanderId}")
	@Operation(summary ="Delete Commander", description = "Delete a Commander in the database")
	public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int commanderId) {
		return service.deleteOne(commanderId);
	}
	
	//delete a commander with a squad/pilots. Reassign the squad/pilots to a new commander
	@DeleteMapping("/{commanderId}/{replacementId}")
	@Operation(summary ="Replace Commander", description = "Find the Commander in the database and replace it. Delete the old Commander and reassign its squadron and pilots to the new one")
	public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int commanderId,@PathVariable int replacementId) {
		return service.deleteOne(commanderId, replacementId);
	}
}

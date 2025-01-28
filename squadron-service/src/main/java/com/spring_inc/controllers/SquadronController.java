package com.spring_inc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_inc.api.CommanderClient;
import com.spring_inc.dtos.CommanderDTO;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.dtos.SquadronDTO;
import com.spring_inc.models.Squadron;
import com.spring_inc.services.SquadronService;

import io.swagger.v3.oas.annotations.Operation;

	@RestController
	@RequestMapping("/squadron")
	public class SquadronController {
		
		private SquadronService service;
		private CommanderClient client;
		
		public SquadronController(SquadronService squadronService, CommanderClient commanderClient) {
			super();
			this.service = squadronService;
			this.client = commanderClient;
		}
		
		// find all
		@GetMapping
		@Operation(summary ="Get All Squadrons", description = "Fetches all Squadrons from the database")
		public ResponseEntity<Iterable<Squadron>> findAll() {
			return service.findAll();
		}
		
		// find one by ID
		@GetMapping("/{squadronId}")
		@Operation(summary ="Find one squadron by Id", description = "Searches the database and finds a squadron based on the given Id")
		public ResponseEntity<Squadron> findById(@PathVariable int squadronId) {
			return service.findById(squadronId);
		}
		
		// add one
		@PostMapping
		@Operation(summary ="Add Squadron", description = "Adds a squadron to the database")
		public ResponseEntity<Squadron> addOne(@RequestBody SquadronDTO squadronDTO) {
			return service.addOne(squadronDTO);
		}
		
		// update one
		@PutMapping("/{squadronId}")
		@Operation(summary ="Update Squadron", description = "Finds a squadron in the database and updates it with the given information")
		public ResponseEntity<Squadron> updateOne(@PathVariable int squadronId, @RequestBody SquadronDTO squadronDTO) {
			return service.updateOne(squadronId, squadronDTO);
		}
		
		// delete one that has a replacement
		@Operation(summary ="Replace Squadron", description = "Find the squadron in the database and replace it. Delete the old squadron and reassign its pilots and commander to the new one")
		@DeleteMapping("/{squadronId}/{replacementId}")
		public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int squadronId, @PathVariable int replacementId) {
			return service.deleteOne(squadronId, replacementId);
		}
		

		// delete one with no relationships
		@DeleteMapping("/{squadronId}")
		@Operation(summary ="Delete Squadron", description = "Delete a squadron in the database")
		public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int squadronId) {
			return service.deleteOne(squadronId);
		}
		
		// get the commander of the squadron
		@GetMapping("/{squadronId}/commander")
		@Operation(summary ="Get Commander", description = "Fetch the commander of a squadron")
		public ResponseEntity<CommanderDTO> getCommander(@PathVariable int squadronId) {
			return service.getCommander(squadronId);
		}
		
		// get the pilots assigned to the squadron
		@GetMapping("/{squadid}/pilots")
		@Operation(summary ="Get Pilots", description = "Fetches all the pilots that are assigned to a squadron")
		public ResponseEntity<Iterable<PilotDTO>> getPilot(@PathVariable int squadid) {
			return service.getPilot(squadid);
		}
		
		// add a pilot to the squadron
		@GetMapping("/{squadronid}/pilot/{pilotid}")
		@Operation(summary ="Add Pilot to Squadron", description = "Add a pilot to a squadron. Checks for full squadrons")
		public ResponseEntity<ResponseDTO> addPilot(@PathVariable int pilotid,@PathVariable int squadronid) {
			return service.addPilot(pilotid, squadronid);
		}
		
	}

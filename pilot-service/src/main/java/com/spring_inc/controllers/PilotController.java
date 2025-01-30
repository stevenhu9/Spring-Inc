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
import com.spring_inc.api.SquadronClient;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.models.Pilot;
import com.spring_inc.services.PilotService;

import io.swagger.v3.oas.annotations.Operation;

	@RestController
	@RequestMapping("/pilot")
	public class PilotController {
		
		private PilotService service;
		private CommanderClient squadronClient;
		
		public PilotController(PilotService pilotService, CommanderClient commanderClient) {
			super();
			this.service = pilotService;
			this.squadronClient = commanderClient;
		}	
		
		// find all
		@GetMapping
		@Operation(summary ="Get All Pilots", description = "Fetches all Pilots from the database")
		public ResponseEntity<Iterable<Pilot>> findAll() {
			return service.findAll();
		}
		
		// find one by ID
		@GetMapping("/{pilotId}")
		@Operation(summary ="Find by Id", description = "Fetches a Pilot based on their ID")
		public ResponseEntity<Pilot> findById(@PathVariable int pilotId) {
			return service.findById(pilotId);
		}
		
		// add one
		@PostMapping
		@Operation(summary ="Add Pilot", description = "Add a Pilot to the database")
		public ResponseEntity<Pilot> addOne(@RequestBody PilotDTO pilotDTO) {
			return service.addOne(pilotDTO);
		}
		
		// update one
		@PutMapping("/{pilotId}")
		@Operation(summary ="Update Pilot", description = "Updates a pilot in the database")
		public ResponseEntity<Pilot> updateOne(@PathVariable int pilotId, @RequestBody PilotDTO pilotDTO) {
			return service.updateOne(pilotId, pilotDTO);
		}
		
		// delete one
		@DeleteMapping("/{pilotId}")
		@Operation(summary ="Delete Pilot", description = "Delete a Pilot in the database")
		public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int pilotId) {
			return service.deleteOne(pilotId);
		} 
		
	}

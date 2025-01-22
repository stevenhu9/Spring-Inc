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
import com.spring_inc.models.Pilot;
import com.spring_inc.services.PilotService;

	@RestController
	@RequestMapping("/pilot")
	public class PilotController {
		
		private PilotService service;
		private SquadronClient squadronClient;
		
		public PilotController(PilotService pilotService, CommanderClient commanderClient) {
			super();
			this.service = pilotService;
			this.squadronClient = squadronClient;
		}	
		
		@GetMapping("/test")
		public String test() {
			return squadronClient.getTest();
		}
		
		
		// find all
		@GetMapping
		public ResponseEntity<Iterable<Pilot>> findAll() {
			return service.findAll();
		}
		
		// find one by ID
		@GetMapping("/{pilotId}")
		public ResponseEntity<Pilot> findById(@PathVariable int pilotId) {
			return service.findById(pilotId);
		}
		
		// add one
		@PostMapping
		public ResponseEntity<Pilot> addOne(@RequestBody PilotDTO pilotDTO) {
			return service.addOne(pilotDTO);
		}
		
		// update one
		@PutMapping("/{pilotId}")
		public ResponseEntity<Pilot> updateOne(@PathVariable int pilotId, @RequestBody PilotDTO pilotDTO) {
			return service.updateOne(pilotId, pilotDTO);
		}
		
		// delete one
		@DeleteMapping("/{pilotId}")
		public ResponseEntity<Void> deleteOne(@PathVariable int pilotId) {
			return service.deleteOne(pilotId);
		} 
		
	}

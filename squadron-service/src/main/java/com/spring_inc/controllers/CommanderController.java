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

	@RestController
	@RequestMapping("/squadron")
	public class CommanderController {
		
		private SquadronService service;
		private CommanderClient client;
		
		public CommanderController(SquadronService squadronService, CommanderClient commanderClient) {
			super();
			this.service = squadronService;
			this.client = commanderClient;
		}
		
		// find all
		@GetMapping
		public ResponseEntity<Iterable<Squadron>> findAll() {
			return service.findAll();
		}
		
		// find one by ID
		@GetMapping("/{squadronId}")
		public ResponseEntity<Squadron> findById(@PathVariable int squadronId) {
			return service.findById(squadronId);
		}
		
		// add one
		@PostMapping
		public ResponseEntity<Squadron> addOne(@RequestBody SquadronDTO squadronDTO) {
			return service.addOne(squadronDTO);
		}
		
		// update one
		@PutMapping("/{squadronId}")
		public ResponseEntity<Squadron> updateOne(@PathVariable int squadronId, @RequestBody SquadronDTO squadronDTO) {
			return service.updateOne(squadronId, squadronDTO);
		}
		
		// delete one that has a replacement
		@DeleteMapping("/{squadronId}/{replacementId}")
		public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int squadronId, @PathVariable int replacementId) {
			return service.deleteOne(squadronId, replacementId);
		}
		

		// delete one with no relationships
		@DeleteMapping("/{squadronId}")
		public ResponseEntity<ResponseDTO> deleteOne(@PathVariable int squadronId) {
			return service.deleteOne(squadronId);
		}
		
		// get the commander of the squadron
		@GetMapping("/{squadronId}/commander")
		public ResponseEntity<CommanderDTO> getCommander(@PathVariable int squadronId) {
			return service.getCommander(squadronId);
		}
		
		// get the pilots assigned to the squadron
		@GetMapping("/pilot/{squadid}")
		public ResponseEntity<Iterable<PilotDTO>> getPilot(@PathVariable int squadid) {
			return service.getPilot(squadid);
		}
		
		// add a pilot to the squadron
		@GetMapping("/{squadronid}/pilot/{pilotid}")
		public ResponseEntity<ResponseDTO> addPilot(@PathVariable int pilotid,@PathVariable int squadronid) {
			return service.addPilot(pilotid, squadronid);
		}
		
	}

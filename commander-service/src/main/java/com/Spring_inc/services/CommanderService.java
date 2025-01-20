package com.Spring_inc.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Spring_inc.dtos.CommanderDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.repositories.CommanderRepository;

@Service
public class CommanderService {

	private final CommanderRepository repo;

	public CommanderService(CommanderRepository repo) {
		super();
		this.repo = repo;
	}
	
	// GET all Commanders
	public ResponseEntity<Iterable<Commander>> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	/*
	// GET Commander by ID
	public ResponseEntity<Commander> findById(int CommanderId) {
		if (repo.existsById(CommanderId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.findById(CommanderId).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
	}
	
	// Create new Commander
	public ResponseEntity<Commander> addOne(CommanderDTO CommanderDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(repo.save(new Commander(0, CommanderDTO.getCommanderName(), CommanderDTO.getrank(), CommanderDTO.getyos(), CommanderDTO.getspecial(), CommanderDTO.getactiveduty())));
	}
	
	// Update existing Commander by ID
	public ResponseEntity<Commander> updateOne(int CommanderId, CommanderDTO CommanderDTO) {
		if (repo.existsById(CommanderId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Commander(0, CommanderDTO.getCommanderName(), CommanderDTO.getrank(), CommanderDTO.getyos(), CommanderDTO.getspecial(), CommanderDTO.getactiveduty())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	// Delete existing Commander
	public ResponseEntity<Void> deleteOne(int CommanderId) {
		repo.deleteById(CommanderId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
							 .body(null);
	}
	*/
}

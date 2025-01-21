package com.spring_inc.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_inc.dtos.SquadronDTO;
import com.spring_inc.models.Squadron;
import com.spring_inc.repositories.SquadronRepository;

@Service
public class SquadronService {

    private final SquadronRepository repo;

	public SquadronService(SquadronRepository repo) {
		super();
		this.repo = repo;
	}
	
	// GET all squadrons
	public ResponseEntity<Iterable<Squadron>> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	
	// GET squadron by ID
	public ResponseEntity<Squadron> findById(int squadronId) {
		if (repo.existsById(squadronId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.findById(squadronId).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
	}
	
	// Create new squadron
	public ResponseEntity<Squadron> addOne(SquadronDTO squadronDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(repo.save(new Squadron(0, squadronDTO.getSquadronName(), squadronDTO.getBase(), squadronDTO.getDateFormed(), squadronDTO.getMission(), squadronDTO.getCapacity(), squadronDTO.getStatus(), squadronDTO.getCommanderId())));
	}
	
	// Update existing squadron by ID
	public ResponseEntity<Squadron> updateOne(int squadronId, SquadronDTO squadronDTO) {
		if (repo.existsById(squadronId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Squadron(0, squadronDTO.getSquadronName(), squadronDTO.getBase(), squadronDTO.getDateFormed(), squadronDTO.getMission(), squadronDTO.getCapacity(), squadronDTO.getStatus(), squadronDTO.getCommanderId())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	// Delete existing squadron
	public ResponseEntity<Void> deleteOne(int squadronId) {
		repo.deleteById(squadronId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
							 .body(null);
	}
}

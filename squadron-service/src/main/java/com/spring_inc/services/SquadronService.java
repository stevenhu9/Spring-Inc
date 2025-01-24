package com.spring_inc.services;

import java.util.List;

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
								 .body(repo.save(new Squadron(squadronId, squadronDTO.getSquadronName(), squadronDTO.getBase(), squadronDTO.getDateFormed(), squadronDTO.getMission(), squadronDTO.getCapacity(), squadronDTO.getStatus(), squadronDTO.getCommanderId())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	// Delete existing squadron
	public ResponseEntity<Void> deleteOne(int squadronId) {
		repo.deleteById(squadronId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
							 .body(null);
		//FIX: when squadrons are deleted, set it up so that each pilot in the squadron has their squadron_id set to -1
	}
	
	// get the commander of the squadron
	public ResponseEntity<Object[]> getCommander(int squadronid){
		if (repo.existsById(squadronid)) {
			int commanderid = repo.getCommanderID(squadronid);
			return ResponseEntity.status(HttpStatus.OK).body(repo.getCommander(commanderid));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(null);
		}
	}
	
	// get a list of the pilots assigned to the squadron
	public ResponseEntity<List<Object[]>> getPilot(int squadid) {
		if (repo.existsById(squadid)) {
			return ResponseEntity.status(HttpStatus.OK).body(repo.getPilot(squadid));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(null);
		}
	}
	
	// add a pilot to the squadron, returns string to user letting them know if it worked
	public ResponseEntity<String> addPilot(int pilotid, int squadronid) {
		//FIX: Add functionality to update the squadrons capacity when pilot is added
		int currentcapacity = repo.checkCapacity(squadronid);
		if(currentcapacity <= 7) {
			repo.addPilot(squadronid, pilotid);
			return ResponseEntity.status(HttpStatus.OK).body("Pilot has been added to the squad");
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The squadron is full, cannot add pilot");
		}
	}
}

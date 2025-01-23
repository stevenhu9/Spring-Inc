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
	
	// GET Commander by ID
	public ResponseEntity<Commander> findById(int commanderId) {
		if (repo.existsById(commanderId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.findById(commanderId).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
	}
	
	// Create new Commander
	public ResponseEntity<Commander> addOne(CommanderDTO commanderDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(repo.save(new Commander(0, commanderDTO.getCommanderName(), commanderDTO.getCommanderRank(), commanderDTO.getYearsOfService(), commanderDTO.getSpecialization(), commanderDTO.getActiveDuty())));
	}
	
	// Update existing Commander by ID
	public ResponseEntity<Commander> updateOne(int commanderId, CommanderDTO commanderDTO) {
		if (repo.existsById(commanderId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Commander(commanderId, commanderDTO.getCommanderName(), commanderDTO.getCommanderRank(), commanderDTO.getYearsOfService(), commanderDTO.getSpecialization(), commanderDTO.getActiveDuty())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	// Delete existing Commander with no relationships
	public ResponseEntity<String> deleteOne(int commanderId) {
		try{
			repo.deleteById(commanderId);
			return ResponseEntity.status(HttpStatus.OK)
							 .body("The commander has been deleted.");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("The commander needs a replacement before it can be deleted");
		}
		
	}
	
	// Delete existing Commander and put in a replacement
		public ResponseEntity<String> deleteOne(int commanderId, int replacementId) {
			try {
				repo.reassignSquad(replacementId,commanderId);
				repo.reassignPilot(replacementId,commanderId);
				repo.deleteById(commanderId);
				return ResponseEntity.status(HttpStatus.OK)
								 	.body("The commander has been replaced");
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Error with replacing commander");
			}
		}

}

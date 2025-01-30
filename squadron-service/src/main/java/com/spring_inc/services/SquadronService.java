package com.spring_inc.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_inc.dtos.CommanderDTO;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
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
	
	// Delete existing squadron with no replacement
	public ResponseEntity<ResponseDTO> deleteOne(int squadronId) {
		try {
		repo.deleteById(squadronId);
		ResponseDTO response = new ResponseDTO("The Squadron has been deleted", true);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(response);
		}catch(Exception e) {
			ResponseDTO response = new ResponseDTO("The Squadron needs to have a replacement", false);
			return ResponseEntity.status(HttpStatus.CONFLICT)
					 .body(response);
		}
	}

	// delete existing squadron with a replacement
	public ResponseEntity<ResponseDTO> deleteOne(int squadronId, int replacementId){
		try {
		repo.deleteById(squadronId);
		ResponseDTO response = new ResponseDTO("The Squadron has been replaced",true);
		return ResponseEntity.status(HttpStatus.OK)
				 .body(response);
		}catch(Exception e) {
			ResponseDTO response = new ResponseDTO("The Squadron cannot be replaced, Error",false);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
	
	// get the commander of the squadron
	public ResponseEntity<CommanderDTO> getCommander(int squadronid){
		if (repo.existsById(squadronid)) {
			int commanderid = repo.getCommanderID(squadronid);
			CommanderDTO temp = repo.getCommander(commanderid);
			System.out.println(temp.toString());
			return ResponseEntity.status(HttpStatus.OK).body(new CommanderDTO(commanderid, temp.getCommanderName(),
					temp.getCommanderRank(), temp.getYearsOfService(), temp.getSpecialization(), temp.getActiveDuty()));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(null);
		}
	}
	
	// get a list of the pilots assigned to the squadron
	public ResponseEntity<Iterable<PilotDTO>> getPilot(int squadid) {
		if (repo.existsById(squadid)) {
			return ResponseEntity.status(HttpStatus.OK).body(repo.getPilot(squadid));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(null);
		}
	}
	
	// add a pilot to the squadron, returns string to user letting them know if it worked
	public ResponseEntity<ResponseDTO> addPilot(int pilotid, int squadronid) {
		int currentcapacity = repo.checkCapacity(squadronid);
		if(currentcapacity <= 7) {
			repo.addPilot(squadronid, pilotid);
			ResponseDTO response = new ResponseDTO("Pilot has been added to the squad", true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			ResponseDTO response = new ResponseDTO("The squad is full, cannot add a pilot", false);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}
}

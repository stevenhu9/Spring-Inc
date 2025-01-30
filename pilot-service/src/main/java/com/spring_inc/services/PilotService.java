package com.spring_inc.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.models.Pilot;
import com.spring_inc.repositories.PilotRepository;

@Service
public class PilotService {

    private final PilotRepository repo;

	public PilotService(PilotRepository repo) {
		super();
		this.repo = repo;
	}
	
	// GET all pilots
	public ResponseEntity<Iterable<Pilot>> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
							 .body(repo.findAll());
	}
	
	// GET pilot by ID
	public ResponseEntity<Pilot> findById(int pilotId) {
		if (repo.existsById(pilotId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.findById(pilotId).get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
	}
	
	// Create new pilot
	public ResponseEntity<Pilot> addOne(PilotDTO pilotDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(repo.save(new Pilot(0, pilotDTO.getPilotRank(), pilotDTO.getFlightHoursLogged(), pilotDTO.getLicense(), pilotDTO.getFullName(), pilotDTO.getAircraft(), pilotDTO.getCommanderId(), pilotDTO.getSquadronId())));
	}
	
	// Update existing pilot by ID
	public ResponseEntity<Pilot> updateOne(int pilotId, PilotDTO pilotDTO) {
		if (repo.existsById(pilotId))
			return ResponseEntity.status(HttpStatus.OK)
								 .body(repo.save(new Pilot(pilotId, pilotDTO.getPilotRank(), pilotDTO.getFlightHoursLogged(), pilotDTO.getLicense(), pilotDTO.getFullName(), pilotDTO.getAircraft(), pilotDTO.getCommanderId(), pilotDTO.getSquadronId())));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								 .body(null);
	}
	
	// Delete existing pilot
	public ResponseEntity<ResponseDTO> deleteOne(int pilotId) {
		try {
		repo.deleteById(pilotId);
		ResponseDTO response = new ResponseDTO("The Pilot has been deleted", true);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(response);
		}catch(Exception e) {
			ResponseDTO response = new ResponseDTO("The Pilot could not be deleted", false);
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body(response);
		}
	}
}

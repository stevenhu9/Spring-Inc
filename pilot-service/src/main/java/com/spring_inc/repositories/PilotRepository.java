package com.spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.spring_inc.models.Pilot;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PilotRepository extends CrudRepository<Pilot, Integer> {

	// Custom queries to access parts of the database for use in the service
	
	@Query(value = "SELECT squadron_id FROM pilot WHERE pilot_id = ?1", nativeQuery = true)
	int getSquadron(int pilotId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE squadron SET capacity = 7 WHERE squadron_id = ?1", nativeQuery = true)
	int removePilot(int squad);
	
}
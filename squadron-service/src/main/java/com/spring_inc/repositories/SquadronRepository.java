package com.Spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.Spring_inc.models.Squadron;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface SquadronRepository extends CrudRepository<Squadron, Integer> {

	@Query(value = "SELECT commander_id FROM squadron WHERE squadron_id = ?1", nativeQuery = true)
	int getCommanderID(int squadronid);
	
	@Query(value = "SELECT * FROM commander WHERE commander_id = ?1", nativeQuery = true)
	Object[] getCommander(int commanderid);
	
	@Query(value = "SELECT * FROM pilot WHERE squadron_id = ?1", nativeQuery = true)
	List<Object[]> getPilot(int id);
	
	@Query(value = "SELECT capacity from squadron WHERE squadron_id = ?1", nativeQuery = true)
	int checkCapacity(int squadid);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE pilot SET squadron_id = ?1 WHERE pilot_id =?2", nativeQuery = true)
	int addPilot(int squadid, int pilotid);
}

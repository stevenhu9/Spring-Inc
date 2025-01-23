package com.Spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.Spring_inc.models.Commander;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CommanderRepository extends CrudRepository<Commander, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE squadron SET commander_id = ?1 WHERE commander_id = ?2", nativeQuery = true)
	void reassignSquad(int replacementId, int commanderid);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE pilot SET commander_id = ?1 WHERE commander_id =?2", nativeQuery = true)
	void reassignPilot(int replacementId, int commanderid);
	
}

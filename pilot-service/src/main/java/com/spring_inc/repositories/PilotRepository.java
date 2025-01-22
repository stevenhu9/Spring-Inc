package com.spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.spring_inc.models.Pilot;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface PilotRepository extends CrudRepository<Pilot, Integer> {

}

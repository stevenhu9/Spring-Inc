package com.spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.spring_inc.models.Squadron;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface SquadronRepository extends CrudRepository<Squadron, Integer> {

}

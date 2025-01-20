package com.Spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.Spring_inc.models.Squadron;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface SquadronRepository extends CrudRepository<Squadron, Integer> {

}

package com.Spring_inc.repositories;

import org.springframework.stereotype.Repository;

import com.Spring_inc.models.Commander;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface CommanderRepository extends CrudRepository<Commander, Integer>{

}

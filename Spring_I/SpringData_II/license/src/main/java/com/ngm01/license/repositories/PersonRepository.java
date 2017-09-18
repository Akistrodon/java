package com.ngm01.license.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ngm01.license.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}

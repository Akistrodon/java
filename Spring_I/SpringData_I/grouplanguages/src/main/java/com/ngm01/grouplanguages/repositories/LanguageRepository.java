package com.ngm01.grouplanguages.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ngm01.grouplanguages.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
	
}

package com.ngm01.license.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ngm01.license.models.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {
}

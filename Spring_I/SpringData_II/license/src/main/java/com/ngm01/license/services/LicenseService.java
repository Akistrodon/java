package com.ngm01.license.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ngm01.license.models.License;
import com.ngm01.license.repositories.LicenseRepository;

@Service
public class LicenseService {

		private LicenseRepository licenseRepo;
		
		public LicenseService(LicenseRepository licenseRepo) {
			this.licenseRepo = licenseRepo;
		}
		
		public List<License> showAll(){
			return (List<License>) licenseRepo.findAll();
		}
		
		public void addLicense(License license) {
			licenseRepo.save(license);
		}
		
		public License getLicense(Long id) {
			return licenseRepo.findOne(id);
		}
		
		public void updateLicense(License license) {
			licenseRepo.save(license);
		}
		
		public void deleteLicense(Long id) {
			licenseRepo.delete(id);
		}
		
}

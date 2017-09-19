package com.ngm01.dojoninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ngm01.dojoninjas.models.Dojo;
import com.ngm01.dojoninjas.repositories.DojoRepository;

@Service
public class DojoService {
	private DojoRepository dojoRepo;
	
	public DojoService(DojoRepository dojoRepo) {
		this.dojoRepo = dojoRepo;
	}
	
	public List<Dojo> showDojos(){
		return (List<Dojo>) dojoRepo.findAll();
	}
	
	public Long addDojo(Dojo dojo) {
		dojoRepo.save(dojo);
		return dojo.getId();
	}
	
	public Dojo getDojo(Long id) {
		return dojoRepo.findOne(id);
	}
	
	public void updateDojo(Dojo dojo) {
		dojoRepo.save(dojo);
	}
	
	public void destroyDojo(Long id) {
		dojoRepo.delete(id);
	}
	
}

package com.ngm01.productscategories.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ngm01.productscategories.models.Category;
import com.ngm01.productscategories.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository catRepo;
	
	public CategoryService(CategoryRepository catRepo) {
		this.catRepo = catRepo;
	}
	
	public Long createCategory(Category category) {
		catRepo.save(category);
		return category.getId();
	}
	
	public List<Category> readAll(){
		return (List<Category>) catRepo.findAll();
	}
	
	public Category readOne(Long id) {
		return catRepo.findOne(id);
	}
	
	public void update(Category category) {
		category.setUpdatedAt(new Date());
		catRepo.save(category);
	}
	
	public void delete(Long id) {
		catRepo.delete(id);
	}
	
}

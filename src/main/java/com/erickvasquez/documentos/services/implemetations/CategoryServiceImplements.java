package com.erickvasquez.documentos.services.implemetations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickvasquez.documentos.models.dtos.SaveCategoryDTO;
import com.erickvasquez.documentos.models.entities.Category;
import com.erickvasquez.documentos.repositories.CategoryRepository;
import com.erickvasquez.documentos.services.CategoryServices;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImplements implements CategoryServices{


	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveCategoryDTO info) throws Exception {
		Category newCategrory = new Category();
		newCategrory.setCode(info.getCode());
		newCategrory.setName(info.getName());
		
		repository.save(newCategrory);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteById(String code) throws Exception {
		repository.deleteById(code);
	}

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Category findOneById(String code) {
		return repository.findById(code).orElse(null);
	}
}

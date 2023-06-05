package com.erickvasquez.documentos.services;

import java.util.List;

import com.erickvasquez.documentos.models.dtos.SaveCategoryDTO;
import com.erickvasquez.documentos.models.entities.Category;

public interface CategoryServices {

public void save(SaveCategoryDTO info) throws Exception;
	
	public void deleteById(String code) throws Exception;
	
	public List<Category> findAll();
	
	public Category findOneById(String code);
}

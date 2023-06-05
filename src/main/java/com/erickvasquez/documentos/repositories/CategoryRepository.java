package com.erickvasquez.documentos.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.erickvasquez.documentos.models.entities.Category;

public interface CategoryRepository extends ListCrudRepository<Category, String> {

	
}

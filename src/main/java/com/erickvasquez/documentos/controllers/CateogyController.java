package com.erickvasquez.documentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickvasquez.documentos.models.dtos.SaveCategoryDTO;
import com.erickvasquez.documentos.models.entities.Category;
import com.erickvasquez.documentos.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CateogyController {

	@Autowired
	private CategoryServices categoryService;
	
	//Get all categories book
	@GetMapping("/all")
	public ResponseEntity<?> findAllCategories() {
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	// Get search code category
	@GetMapping("/{id}")
	public ResponseEntity<?> findOneCategory(@PathVariable(name = "id") String id) {
		Category category = categoryService.findOneById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	// Save Category
	@PostMapping("")
	public ResponseEntity<?> saveCategory(@ModelAttribute @Valid SaveCategoryDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>("La cago", HttpStatus.BAD_REQUEST);
		}
		
		try {
			categoryService.save(info);
			return new ResponseEntity<>("Buenale", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

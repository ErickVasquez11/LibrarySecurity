package com.erickvasquez.documentos.services;

import java.util.List;

import com.erickvasquez.documentos.models.dtos.SaveBookDTO;
import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.models.entities.Category;
import com.erickvasquez.documentos.models.entities.User;

public interface BookServices {
	

	void save(SaveBookDTO data, Category category) throws Exception;
	
	void deleteById(String id) throws Exception;
	
	Book findOneById (String id);
	
	List<Book> findAll();
	
	List<Book> findAllByISBN (String isbn);
	
	Book findByCode (String code);
	
	void createPres(Book book, User user) throws Exception;
}

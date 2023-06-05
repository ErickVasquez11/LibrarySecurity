package com.erickvasquez.documentos.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.erickvasquez.documentos.models.entities.Book;

public interface BookRepository extends ListCrudRepository<Book, UUID> {
	
	List<Book> findByIsbn(String Isbn);

}

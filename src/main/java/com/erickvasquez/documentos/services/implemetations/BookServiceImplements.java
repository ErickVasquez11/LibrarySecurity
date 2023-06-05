package com.erickvasquez.documentos.services.implemetations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickvasquez.documentos.models.dtos.SaveBookDTO;
import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.models.entities.BookPres;
import com.erickvasquez.documentos.models.entities.Category;
import com.erickvasquez.documentos.models.entities.User;
import com.erickvasquez.documentos.repositories.BookRepository;
import com.erickvasquez.documentos.services.BookServices;

import jakarta.transaction.Transactional;

@Service 
public class BookServiceImplements implements BookServices{
	
	@Autowired
	private BookRepository repository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save (SaveBookDTO data, Category category) throws Exception {
		Book book = new Book( data.getIsbn(), data.getTitle(), data.getPublishDate(), category);
		
		repository.save(book);
	}
	
	//Delete by id
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteById(String id) throws Exception {
		UUID code = UUID.fromString(id);
		repository.deleteById(code);
	}
	
	// search book with id
	@Override
	public Book findOneById(String id) {
		try {
			UUID code = UUID.fromString(id);
			return repository.findById(code).orElse(null);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	//search all book
	@Override
	public List<Book> findAll() {
		return repository.findAll();
	}
	
	//Search with isbn
	@Override
	public List<Book> findAllByISBN(String isbn) {
		return repository.findByIsbn(isbn);
	}
	
	//Search with code
	@Override
	public Book findByCode(String code) {
		try {
			UUID _code = UUID.fromString(code);
			return repository.findById(_code).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	//Create Press
	@Override
	public void createPres(Book book, User user) throws Exception {
		BookPres pres = new BookPres( new Date(), new Date(), user, book
				);
		
		List<BookPres> loans = book.getBookPres();
		loans.add(pres);
		
		repository.save(book);
	}

}

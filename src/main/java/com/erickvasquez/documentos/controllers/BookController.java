package com.erickvasquez.documentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickvasquez.documentos.models.dtos.BookPresDTO;
import com.erickvasquez.documentos.models.dtos.SaveBookDTO;
import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.models.entities.BookPres;
import com.erickvasquez.documentos.models.entities.Category;
import com.erickvasquez.documentos.models.entities.User;
import com.erickvasquez.documentos.services.BookPresServices;
import com.erickvasquez.documentos.services.BookServices;
import com.erickvasquez.documentos.services.CategoryServices;
import com.erickvasquez.documentos.services.UserServices;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class BookController {

	
	@Autowired
	private BookServices bookServices;
	
	@Autowired
	private BookPresServices bookPresServices;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private CategoryServices categoryServices;
	
	
	//Get List book
	@GetMapping("/collection")
	private ResponseEntity<?> findAllBook() {
		List<Book> books = bookServices.findAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	// Get all book category
	@GetMapping("/collection/{cat}")
	private ResponseEntity<?> findAllBooksByCategory(@PathVariable String cat) {
		Category category = categoryServices.findOneById(cat);
		
		if (category == null)
			return new ResponseEntity<>("No existe esa m...", HttpStatus.NOT_FOUND);
					
		List<Book> books = category.getBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	//Post Create Book
	@PostMapping("")
	private ResponseEntity<?> createBook(@ModelAttribute SaveBookDTO data) {
		Category category = categoryServices.findOneById(data.getCategory());
		
		if (category == null)
			return new ResponseEntity<>("No existe esa m...", HttpStatus.NOT_FOUND);
		
		try {
			bookServices.save(data, category);
			return new ResponseEntity<>("Libro creado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// Get search book with isbn
	@GetMapping("/collection/isbn/{isbn}")
	private ResponseEntity<?> findAllBookByIsbn(@PathVariable String isbn) {
		List<Book> books = bookServices.findAllByISBN(isbn);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	// Get Pres book
	@GetMapping("/pres/code/{code}")
	private ResponseEntity<?> findPresByBook(@PathVariable String code) {
		Book book = bookServices.findByCode(code);
		List<BookPres> bookPres = book.getBookPres();
		
		BookPres currentPres = bookPres.stream()
				.filter(loan -> loan.getReturnDate() == null)
				.findFirst()
				.orElse(null);
		
		return new ResponseEntity<>(currentPres, HttpStatus.OK);
	}
	
	//Get collections press book
	@GetMapping("/pres/collection/code/{code}")
	private ResponseEntity<?> findLoansByBook(@PathVariable String code) {
		Book book = bookServices.findByCode(code);
		
		if (book == null)
			return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
		
		List<BookPres> bookPres = book.getBookPres();		
		return new ResponseEntity<>(bookPres, HttpStatus.OK);
	}
	
	//Get press colletion with user
	@GetMapping("/pres/collection/user/{id}")
	private ResponseEntity<?> findLoansByUser(@PathVariable String id) {
		User user = userServices.findOneById(id);
		
		if (user == null)
			return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		
		// TODO: use response DTO
		
		List<BookPres> bookPres = user.getBookPress();		
		return new ResponseEntity<>(bookPres, HttpStatus.OK);
	}
	
	@PostMapping("/loans")
	private ResponseEntity<?> createBookLoan(@ModelAttribute BookPresDTO data) {
		Book book = bookServices.findByCode(data.getBookCode());
		User user = userServices.findOneById(data.getUserCode());
		
		if (book == null || user == null)
			return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
		
		// TODO: validate book availability
		
		try {
			bookPresServices.createPres(book, user);
			return new ResponseEntity<>("book loan created", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/loans/close-loan/{code}")
	private ResponseEntity<?> closeBookLoan(@PathVariable String code) {
		return new ResponseEntity<>("book loan closed", HttpStatus.OK);
	}
}

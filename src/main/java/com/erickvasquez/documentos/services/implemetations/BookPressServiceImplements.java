package com.erickvasquez.documentos.services.implemetations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.models.entities.BookPres;
import com.erickvasquez.documentos.models.entities.User;
import com.erickvasquez.documentos.repositories.BookPresRepository;
import com.erickvasquez.documentos.services.BookPresServices;

@Service
public class BookPressServiceImplements implements BookPresServices {

	@Autowired
	private BookPresRepository repository;
	
	@Override
	public void closeBookPress(String bookCode) throws Exception {
		
	}
	
	@Override
	public void createPres(Book book, User user) throws Exception{
		
		BookPres pres = new BookPres( new Date(), new Date(), user, book);
		
		repository.save(pres);
	}
}

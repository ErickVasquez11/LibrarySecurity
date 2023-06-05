package com.erickvasquez.documentos.services;

import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.models.entities.User;

public interface BookPresServices {
	
	void createPres(Book book, User user) throws Exception;
	
	void closeBookPress(String bookCode) throws Exception;
}

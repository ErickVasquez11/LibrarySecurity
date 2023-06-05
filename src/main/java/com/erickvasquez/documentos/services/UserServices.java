package com.erickvasquez.documentos.services;

import java.util.List;

import com.erickvasquez.documentos.models.dtos.RegisterUserDTO;
import com.erickvasquez.documentos.models.dtos.UpdateUserDTO;
import com.erickvasquez.documentos.models.entities.User;


public interface UserServices {
	
	List<User> findAll();
	User findOneById(String id);
	void register(RegisterUserDTO data) throws Exception;
	void update(UpdateUserDTO data) throws Exception;
	
	Boolean comparePassword(String toCompare, String actual);
}

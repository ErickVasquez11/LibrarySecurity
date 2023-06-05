package com.erickvasquez.documentos.services;

import com.erickvasquez.documentos.models.dtos.ChagePasswordDTO;
import com.erickvasquez.documentos.models.dtos.LoginDTO;
import com.erickvasquez.documentos.models.entities.User;

public interface AuthServices {
	
	User LogIn(LoginDTO data);
	void changePassword(ChagePasswordDTO data) throws Exception;

}

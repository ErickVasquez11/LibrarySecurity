package com.erickvasquez.documentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erickvasquez.documentos.models.dtos.ChagePasswordDTO;
import com.erickvasquez.documentos.models.dtos.LoginDTO;
import com.erickvasquez.documentos.models.dtos.RegisterUserDTO;
import com.erickvasquez.documentos.models.dtos.UpdateUserDTO;
import com.erickvasquez.documentos.models.entities.User;
import com.erickvasquez.documentos.services.AuthServices;
import com.erickvasquez.documentos.services.UserServices;
import com.erickvasquez.documentos.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	
	    //  registrarse
		//  ruta que logee, usuario o correo, contrasena
		//  dado un usuario permita modificar el nombre
		//  cambiar contrasena
		// TODO:  añadir campo active y hacer el toggle
			
		@Autowired
		private AuthServices authServices;
		
		@Autowired
		private UserServices userServices;
		
		@Autowired
		private RequestErrorHandler errorHandler;
		
		@PostMapping("/login")
		private ResponseEntity<?> logIn(@ModelAttribute @Valid LoginDTO data, BindingResult validations) {
			
			if (validations.hasErrors()) {
				return new ResponseEntity<>(
						errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
			}
			
			User user = authServices.LogIn(data);
			//User user = userServices.findOneById(data.getId());
			if (user == null)
				return new ResponseEntity<>("invalid credentials", HttpStatus.NOT_FOUND);
			
			Boolean validPassword = userServices.comparePassword(data.getPassword(), user.getPassword());
			if (!validPassword)
				return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
			
			return new ResponseEntity<>("logged in", HttpStatus.OK);
		}
		
		@PostMapping("")
		private ResponseEntity<?> register(@ModelAttribute RegisterUserDTO data) {
			try {
				userServices.register(data);
				return new ResponseEntity<>("user created", HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}
			
		@PutMapping("")
		private ResponseEntity<?> udpateUserName(@ModelAttribute UpdateUserDTO data) {
			try {
				User user = userServices.findOneById(data.getId());
				
				if (user == null)
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
				userServices.update(data);
				return new ResponseEntity<>("user updated", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		@PatchMapping("/change-password")
		private ResponseEntity<?> changePassword(@ModelAttribute ChagePasswordDTO data) {
			try {
				User user = userServices.findOneById(data.getId());
				if (user == null)
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
				// TODO: notify if current password is incorrect
				authServices.changePassword(data);
				return new ResponseEntity<>("password changed", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}		
		}
}

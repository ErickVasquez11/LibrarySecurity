package com.erickvasquez.documentos.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDTO {

	@NotEmpty(message = "Warn! Username is required")
	private String username;
	
	@NotEmpty(message = "Warn! Email is required")
	private String email;
	
	@NotEmpty(message = "Warn! Password is required")
	private String password;

	@NotEmpty(message = "Warn! Name is required")
	private String name;
}

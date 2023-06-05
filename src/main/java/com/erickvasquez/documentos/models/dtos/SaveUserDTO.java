package com.erickvasquez.documentos.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveUserDTO {
	@NotBlank(message = "Warn! Username is required")
	private String username;
	
	@NotBlank(message = "Warn! Email is required")
	private String email;
	
	@NotBlank(message = "Warn! Password is required")
	private String password;
	
	@NotBlank(message = "Warn! Name is required")
	private String name;
}

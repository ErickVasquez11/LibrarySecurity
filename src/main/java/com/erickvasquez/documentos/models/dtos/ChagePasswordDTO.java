package com.erickvasquez.documentos.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChagePasswordDTO {

	@NotEmpty(message= "Warn! Identifier is required")
	private String id;
	
	@NotEmpty(message= "Warn! Old password is required")
	private String password;
	
	@NotEmpty(message = "Warn! New password is required")
	private String newPassword;
}

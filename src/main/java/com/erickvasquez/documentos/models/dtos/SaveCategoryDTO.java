package com.erickvasquez.documentos.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveCategoryDTO {
	
	@NotEmpty(message = "Warn! Code is required")
	@Pattern(regexp = "^[0-9A-Z]{4}$", message = "Error! Code must be 4 alphanumeric characters")
	private String code;
	
	@NotEmpty(message = "Warn! Name is required")
	@Size(min = 5, message = "Name size is 5 chars")
	private String name;
}

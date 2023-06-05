package com.erickvasquez.documentos.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookPresDTO {
	
	@NotBlank(message = "Warn! Book code required")
	private String bookCode;
	
	@NotBlank(message = "Warn! User code required")
	private String userCode;
}

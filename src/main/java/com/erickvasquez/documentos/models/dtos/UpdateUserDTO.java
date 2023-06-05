package com.erickvasquez.documentos.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {

	private String id;
	private String name;
}

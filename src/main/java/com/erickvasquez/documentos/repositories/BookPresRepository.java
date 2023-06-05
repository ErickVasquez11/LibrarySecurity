package com.erickvasquez.documentos.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.erickvasquez.documentos.models.entities.BookPres;

public interface BookPresRepository  extends ListCrudRepository<BookPres, UUID>{

}

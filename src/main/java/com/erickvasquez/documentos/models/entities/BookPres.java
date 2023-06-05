package com.erickvasquez.documentos.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookpres")
public class BookPres {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code")
	private UUID code;
	
	@Column(name = "pres_date")
	private Date presDate;
	
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "return_date", nullable = true)
	private Date returnDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_code", nullable = true)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_code", nullable = true)
	private Book book;

	public BookPres(Date presDate, Date expDate, User user, Book book) {
		super();
		this.presDate = presDate;
		this.expDate = expDate;
		this.user = user;
		this.book = book;
	}
}

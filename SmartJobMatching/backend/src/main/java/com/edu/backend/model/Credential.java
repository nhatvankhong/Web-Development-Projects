package com.edu.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "credentials")
public class Credential implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "username", nullable=true, length = 20)
	private String username;

	@Column(name = "password", nullable=true, length = 50)
	private String password;
	
	public Credential() {
		super();
	}
}
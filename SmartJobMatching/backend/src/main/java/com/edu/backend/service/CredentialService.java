package com.edu.backend.service;

import java.util.List;

import com.edu.backend.model.Credential;

public interface CredentialService {

	void deleteCredential(Credential credential);
	
	void updateCredential(Credential credential);
	
	Credential getCredential(String username);
	
	Credential getCredential(String username, String password);
	
	Credential addCredential(Credential credential);
	
	List<Credential> getAllCredentials();
}
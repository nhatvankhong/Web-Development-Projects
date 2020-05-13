package com.edu.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.backend.model.Credential;
import com.edu.backend.repository.CredentialRepository;
import com.edu.backend.service.CredentialService;

@Service("credentialService")
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private CredentialRepository credentialRepository;
	
	public CredentialServiceImpl() {}
	
	public Credential addCredential(Credential credential) {
		return this.credentialRepository.save(credential);
	}
	
	public List<Credential> getAllCredentials() {
		return (List<Credential>)credentialRepository.findAll();
	}

	public void deleteCredential(Credential credential) {
		credentialRepository.delete(credential);
	}

	public void updateCredential(Credential credential) {
		credentialRepository.save(credential);
	}
	
	public Credential getCredential(String username) {
		return credentialRepository.getCredential(username);
	}
	
	public Credential getCredential(String username, String password) {
		return credentialRepository.getCredential(username, password);
	}
}
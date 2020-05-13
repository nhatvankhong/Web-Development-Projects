package com.edu.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.backend.model.Credential;

@Repository
@Transactional
public interface CredentialRepository extends CrudRepository<Credential, Long> {
	
	@Query(value = "SELECT * FROM credentials wk  WHERE wk.username =(:username) AND wk.password =(:password)", nativeQuery = true)
	Credential getCredential(@Param("username") String username, @Param("password") String password);
	
	@Query(value = "SELECT * FROM credentials wk  WHERE wk.username =(:username)", nativeQuery = true)
	Credential getCredential(@Param("username") String username);
}
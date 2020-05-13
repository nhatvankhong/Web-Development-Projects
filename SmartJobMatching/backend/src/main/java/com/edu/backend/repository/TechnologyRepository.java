package com.edu.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.backend.model.Technology;

@Repository
@Transactional
public interface TechnologyRepository extends CrudRepository<Technology, Long> {
	
	@Query(value = "SELECT * FROM technologies wk  WHERE wk.name_level =(:nameLevel)", nativeQuery = true)
	Technology getTechnology(@Param("nameLevel") String nameLevel);
}
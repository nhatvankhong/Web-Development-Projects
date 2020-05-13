package com.edu.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.backend.model.Skill;

@Repository
@Transactional
public interface SkillRepository extends CrudRepository<Skill, Long> {
	
	@Query(value = "SELECT * FROM skills wk  WHERE wk.name_level =(:nameLevel)", nativeQuery = true)
	Skill getSkill(@Param("nameLevel") String nameLevel);
}
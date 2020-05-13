package com.edu.backend.service;

import java.util.List;

import com.edu.backend.model.Skill;

public interface SkillService {

	void deleteSkill(Skill skill);
	
	void updateSkill(Skill skill);
	
	Skill addSkill(Skill skill);
	
	List<Skill> getAllSkills();
	
	Skill getSkill(String nameLevel);
}
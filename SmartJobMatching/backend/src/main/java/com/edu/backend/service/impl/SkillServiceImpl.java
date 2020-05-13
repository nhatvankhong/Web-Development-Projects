package com.edu.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.backend.model.Skill;
import com.edu.backend.repository.SkillRepository;
import com.edu.backend.service.SkillService;

@Service("skillService")
public class SkillServiceImpl implements SkillService {
	
	@Autowired
	private SkillRepository skillRepository;
	
	public SkillServiceImpl() {}
	
	public Skill addSkill(Skill skill) {
		return this.skillRepository.save(skill);
	}
	
	public List<Skill> getAllSkills() {
		return (List<Skill>)skillRepository.findAll();
	}

	public void deleteSkill(Skill skill) {
		skillRepository.delete(skill);
	}

	public void updateSkill(Skill skill) {
		skillRepository.save(skill);
	}

	@Override
	public Skill getSkill(String nameLevel) {
		return skillRepository.getSkill(nameLevel);
	}
}
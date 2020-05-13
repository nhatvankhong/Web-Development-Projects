package com.edu.backend.model.dto;

import java.io.Serializable;

import com.edu.backend.model.Skill;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SkillDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long skillId;
	
	private String name_level;
	
	private String name;

	private String level;
	
	public SkillDTO() {
		super();
	}
	
	public SkillDTO(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.name_level = skill.getName_level();
		this.name = skill.getName();
		this.level = skill.getLevel();
	}
}
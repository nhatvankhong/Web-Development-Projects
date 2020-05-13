package com.edu.backend.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.edu.backend.model.Skill;
import com.edu.backend.model.Technology;
import com.edu.backend.model.UserProfile;

import lombok.Data;

@Data
public class UserProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long profileId;

	private String jobExpected;
	
	private String experience;
	
	private Set<TechnologyDTO> technologies;
	
	private Set<SkillDTO> skills;

	public UserProfileDTO() {
		super();
	}
	
	public UserProfileDTO(UserProfile userProfile) {
		super();
		this.profileId = userProfile.getProfileId();
		this.jobExpected = userProfile.getJobExpected();
		this.experience = userProfile.getExperience();
		this.skills = new HashSet<>();
		this.technologies = new HashSet<>();
		
		for (Skill skill : userProfile.getSkills()) {
			this.skills.add(new SkillDTO(skill));
		}
		
		for (Technology technology : userProfile.getTechnologies()) {
			this.technologies.add(new TechnologyDTO(technology));
		}
	}
}
package com.edu.backend.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.edu.backend.model.Job;
import com.edu.backend.model.Skill;
import com.edu.backend.model.Technology;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, exclude = {"skills", "technologies", "missedSkills"})
public class JobDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long jobId;

	private String title;

	private String desc;

	private String location;
	
	private boolean isFullTime;

	private double salary;

	private String experience;
	
	private double matchedPercent = 0.0;
	
	private Set<SkillDTO> skills;

	private Set<TechnologyDTO> technologies;

	private Set<SkillDTO> missedSkills;
	
	public JobDTO() {
		super();
	}
	
	public JobDTO(Job job) {
		super();
		this.jobId = job.getJobId();
		this.title = job.getTitle();
		this.desc = job.getDesc();
		this.location = job.getLocation();
		this.isFullTime = job.isFullTime();
		this.salary = job.getSalary();
		this.experience = job.getExperience();
		this.matchedPercent = job.getMatchedPercent();
		this.skills = new HashSet<>();
		this.technologies = new HashSet<>();
		this.missedSkills = new HashSet<>();
		
		for (Skill skill : job.getSkills()) {
			this.skills.add(new SkillDTO(skill));
		}
		
		for (Technology technology : job.getTechnologies()) {
			this.technologies.add(new TechnologyDTO(technology));
		}
		
		for (Skill skill : job.getMissedSkills()) {
			this.missedSkills.add(new SkillDTO(skill));
		}
	}
}
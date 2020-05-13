package com.edu.backend.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.edu.backend.model.Job;
import com.edu.backend.model.Skill;
import com.edu.backend.model.Technology;
import com.edu.backend.model.UserProfile;

public class JobMatchingUtil {

	public static List<Job> matching(UserProfile userProfile, List<Job> matchedJobs) {

		for (Job job : matchedJobs) {
			int mark = 0;
			Set<Skill> missedSkills = new HashSet<>();

			//Match job title
			if (userProfile.getJobExpected().contains(job.getTitle())) {
				mark += 10;
			}

			//Match experience
			if (Integer.parseInt(job.getExperience().substring(0, 1)) <= Integer.parseInt(userProfile.getExperience().substring(0,1)) &&
					Integer.parseInt(job.getExperience().substring(job.getExperience().length() - 1)) <= 
					Integer.parseInt(userProfile.getExperience().substring(userProfile.getExperience().length() - 1))) {
				mark += 10;
			}

			//Compare skills
			mark += compareSkills(job.getSkills(), userProfile.getSkills());

			//Compare technologies
			mark += compareTechnologies(job.getTechnologies(), userProfile.getTechnologies());

			//Get missed skills
			missedSkills = getMissedSkills(job.getSkills(), userProfile.getSkills());

			job.setMatchedPercent(mark);
			job.setMissedSkills(missedSkills);
		}

		return matchedJobs;
	}

	public static int compareSkills(Set<Skill> jSkills, Set<Skill> uSkills) {
		int mark = 0;

		for(Skill uSkill : uSkills) {          
			for(Skill jSkill : jSkills) {
				if (uSkill.getName() != null && jSkill.getName() != null) {
					if(uSkill.getName().equals(jSkill.getName())){
						if (Integer.parseInt(uSkill.getLevel()) >= Integer.parseInt(jSkill.getLevel())) {
							mark += 5;
							break;
						}
						else {
							mark += 1;
							break;
						}
					}
				}
			}
		}

		return mark;
	}

	public static int compareTechnologies(Set<Technology> jTechs, Set<Technology> uTechs) {
		int mark = 0;

		for(Technology uTech : uTechs) {          
			for(Technology jTech : jTechs) {
				if(uTech.getName() != null  && jTech.getName() != null){
					if(uTech.getName().equals(jTech.getName())){
						if (Integer.parseInt(uTech.getLevel()) >= Integer.parseInt(jTech.getLevel())) {
							mark += 5;
							break;
						}
						else {
							mark += 1;
							break;
						}
					}
				}
			}
		}

		return mark;
	}

	public static Set<Skill> getMissedSkills(Set<Skill> jSkills, Set<Skill> uSkills) {
		Set<Skill> missedTechs = new HashSet<Skill>();

		for(Skill jSkill : jSkills) {          
			int count = 0;
			for(Skill uSkill : uSkills) {
				if(uSkill.getName() != null  && jSkill.getName() != null){
					if(uSkill.getName().equals(jSkill.getName())){
						count++;
						break;
					}
				}
			}
			
			if (count == 0) {
				missedTechs.add(jSkill);
			}
		}
		return missedTechs;
	}
}
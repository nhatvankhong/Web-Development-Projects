package com.edu.backend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "profiles")
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "profile_id")
	private long profileId;
	
	@Column(name = "job_expected", nullable=true, length = 500)
	private String jobExpected;
	
	@Column(name = "experience", nullable=true, length = 500)
	private String experience;
	
	@ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinTable(name = "profile_technology_mappings", joinColumns = {@JoinColumn(name = "profile_id", unique = false)},
    inverseJoinColumns = {@JoinColumn(name = "tech_id", unique = false)})
	private Set<Technology> technologies;
	
	@ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinTable(name = "profile_skill_mappings", joinColumns = {@JoinColumn(name = "profile_id", unique = false)},
    inverseJoinColumns = {@JoinColumn(name = "skill_id", unique = false)})
	private Set<Skill> skills;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
	private Credential credential;

	public UserProfile() {
		super();
	}
}
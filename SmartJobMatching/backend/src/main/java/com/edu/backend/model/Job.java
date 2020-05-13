package com.edu.backend.model;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "jobs")
@EqualsAndHashCode(callSuper = false, exclude = {"skills", "technologies", "missedSkills"})
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "job_id")
	private long jobId;
	
	@Column(name = "title", nullable=true, length = 30)
	private String title;
	
	@Column(name = "description", nullable=true, length = 500)
	private String desc;
	
	@Column(name = "location", nullable=true, length = 30)
	private String location;
	
	@Column(name = "is_full_time")
	private boolean isFullTime;
	
	@Column(name = "salary")
	private double salary;
	
	@Column(name = "experience", nullable=true, length = 30)
	private String experience;
	
	@Transient
	private double matchedPercent = 0.0;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "job_skill_mappings",
        joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"))
	@Fetch(FetchMode.JOIN)
	private Set<Skill> skills;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "job_technology_mappings",
        joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "tech_id", referencedColumnName = "tech_id"))
	@Fetch(FetchMode.JOIN)
	private Set<Technology> technologies;
	
	@Transient
	private Set<Skill> missedSkills;
	
	public Job() {
		super();
		this.missedSkills = new HashSet<>();
	}
}
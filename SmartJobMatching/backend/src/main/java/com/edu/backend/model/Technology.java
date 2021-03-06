package com.edu.backend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "technologies")
@EqualsAndHashCode(callSuper = false, exclude = {"userProfiles", "jobs"})
public class Technology implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tech_id")
	private long techId;
	
	@Column(name = "name_level", nullable=true, length = 30)
	private String name_level;
	
	@Column(name = "name", nullable=true, length = 30)
	private String name;

	@Column(name = "level", nullable=true, length = 20)
	private String level;
	
	@ManyToMany(mappedBy = "technologies", fetch = FetchType.EAGER)
	private Set<UserProfile> userProfiles;
	
	@ManyToMany(mappedBy = "technologies", fetch = FetchType.EAGER)
    private Set<Job> jobs;
	
	public Technology() {
		super();
	}
}
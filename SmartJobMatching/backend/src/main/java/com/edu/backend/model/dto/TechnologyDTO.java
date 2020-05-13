package com.edu.backend.model.dto;

import java.io.Serializable;

import com.edu.backend.model.Technology;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TechnologyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long techId;

	private String name_level;

	private String name;

	private String level;
	
	public TechnologyDTO() {
		super();
	}
	
	public TechnologyDTO(Technology technology) {
		super();
		this.techId = technology.getTechId();
		this.name_level = technology.getName_level();
		this.name = technology.getName();
		this.level = technology.getLevel();
	}
}
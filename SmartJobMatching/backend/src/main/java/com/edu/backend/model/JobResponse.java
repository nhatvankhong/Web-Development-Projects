package com.edu.backend.model;

import java.util.List;

import com.edu.backend.model.dto.JobDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, exclude = {"jobs"})
public class JobResponse extends AuthResponse {

	private static final long serialVersionUID = 2319497642843948222L;
	
	private List<JobDTO> jobs;
	
	public JobResponse() {
		super();
	}
	
	public JobResponse(long userId, boolean hasProfile, List<JobDTO> jobs, String message) {
		super();
		this.userId = userId;
		this.hasProfile = hasProfile;
		this.message = message;
		this.jobs = jobs;
	}
}
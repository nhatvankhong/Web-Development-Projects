package com.edu.backend.service;

import java.util.List;

import com.edu.backend.model.Job;

public interface JobService {

	void deleteJob(Job job);
	
	void updateJob(Job job);
	
	Job addJob(Job job);
	
	List<Job> getAllJobs();
}
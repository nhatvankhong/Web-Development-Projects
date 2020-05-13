package com.edu.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.backend.model.Job;
import com.edu.backend.repository.JobRepository;
import com.edu.backend.service.JobService;

@Service("jobService")
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	public JobServiceImpl() {}
	
	public Job addJob(Job job) {
		return this.jobRepository.save(job);
	}
	
	public List<Job> getAllJobs() {
		return (List<Job>)jobRepository.findAll();
	}
	
	public void deleteJob(Job job) {
		jobRepository.delete(job);
	}

	public void updateJob(Job job) {
		jobRepository.save(job);
	}
}
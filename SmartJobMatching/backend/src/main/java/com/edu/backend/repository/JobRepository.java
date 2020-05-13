package com.edu.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edu.backend.model.Job;

@Repository
@Transactional
public interface JobRepository extends CrudRepository<Job, Long> {}
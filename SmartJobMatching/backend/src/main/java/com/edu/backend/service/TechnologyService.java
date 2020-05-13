package com.edu.backend.service;

import java.util.List;

import com.edu.backend.model.Technology;

public interface TechnologyService {

	void deleteTechnology(Technology technology);
	
	void updateTechnology(Technology technology);
	
	Technology addTechnology(Technology technology);
	
	List<Technology> getAllTechnologies();
	
	Technology getTechnology(String nameLevel);
}
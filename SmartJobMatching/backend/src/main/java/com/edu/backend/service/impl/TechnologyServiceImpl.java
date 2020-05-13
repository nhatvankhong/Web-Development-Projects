package com.edu.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.backend.model.Technology;
import com.edu.backend.repository.TechnologyRepository;
import com.edu.backend.service.TechnologyService;

@Service("technologyService")
public class TechnologyServiceImpl implements TechnologyService {
	
	@Autowired
	private TechnologyRepository technologyRepository;
	
	public TechnologyServiceImpl() {}
	
	public Technology addTechnology(Technology technology) {
		return this.technologyRepository.save(technology);
	}
	
	public List<Technology> getAllTechnologies() {
		return (List<Technology>)technologyRepository.findAll();
	}
	
	public void deleteTechnology(Technology technology) {
		technologyRepository.delete(technology);
	}

	public void updateTechnology(Technology technology) {
		technologyRepository.save(technology);
	}

	@Override
	public Technology getTechnology(String nameLevel) {
		return technologyRepository.getTechnology(nameLevel);
	}
}
package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.JobExperience;
import com.paraamarsh.jobpost.repository.ExperienceRepository;
import com.paraamarsh.jobpost.service.ExperienceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	private static final Logger logger = LoggerFactory.getLogger( ExperienceServiceImpl.class);
    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public List<JobExperience> findAll() {
    	logger.debug(" fetchALL from experience{}");
        return experienceRepository.findAll();
    }

    @Override
    public JobExperience findById(String id) {
    	logger.debug("fetchAll from experience{}",id);
        return experienceRepository.findById(id).get();
    }

    @Override
    public void save(JobExperience entity) {
        experienceRepository.save(entity);
        logger.debug("update from experience{}",entity);
    }

    @Override
    public void delete(String id) {
    	logger.debug("delete from experience{}",id);
        experienceRepository.deleteById(id);
    }

}

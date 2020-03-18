package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.JobPosition;
import com.paraamarsh.jobpost.repository.JobPositionRepository;
import com.paraamarsh.jobpost.service.PositionNameService;
import com.paraamarsh.jobpost.web.rest.PositionNameController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionNameServiceImpl implements PositionNameService {
	 private static final Logger logger = LoggerFactory.getLogger(PositionNameController.class);


    @Autowired
    JobPositionRepository jobPositionRepository;

    @Override
    public List<JobPosition> findAll() {
    	logger.info("Request for fetchall from JobPosition{}");
        return jobPositionRepository.findAll();
    }

    @Override
    public JobPosition findById(String id) {
    	logger.info("Request for fetchallById from JobPosition{}",id);
        return jobPositionRepository.findById(id).get();
    }

    @Override
    public void save(JobPosition entity) {
    	logger.debug("Request for save  JobPosition{}",entity);
        jobPositionRepository.save(entity);
    }

    @Override
    public void delete(String id) {
    	logger.info("Request for delete JobPosition{}",id);
        jobPositionRepository.deleteById(id);
    }
}


package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.JobNo;
import com.paraamarsh.jobpost.repository.JobNoRespository;
import com.paraamarsh.jobpost.service.JobNoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobNoServiceImpl implements JobNoService {
	private static final Logger logger = LoggerFactory.getLogger( JobNoServiceImpl.class);


    @Autowired
    JobNoRespository jobNoRespository;

    @Override
    public List<JobNo> findAll() {
    	logger.debug(" fetchALL from jobNo{}");
        return jobNoRespository.findAll();
    }

    @Override
    public JobNo findById(String id) {
    	logger.debug("Request for fetchALL from jobNo{}",id);
        return jobNoRespository.findById(id).get();
    }

    @Override
    public void save(JobNo entity) {
    	logger.debug("Request for update jobNo{}",entity);
        jobNoRespository.save(entity);
    }

    @Override
    public void delete(String id) {
    	logger.debug("Request for delete jobNo{}",id);
        jobNoRespository.deleteById(id);
    }

}

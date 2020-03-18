package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.UserName;
import com.paraamarsh.jobpost.repository.UserNameRepository;
import com.paraamarsh.jobpost.service.UserNameService;
import com.paraamarsh.jobpost.web.rest.ExperienceController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNameServiceImpl implements UserNameService {
	private static final Logger logger = LoggerFactory.getLogger(UserNameServiceImpl.class);

    @Autowired
    UserNameRepository userNameRepository;

    @Override
    public List<UserName> findAll() {
    	logger.info("Request for fetchAll from userName");
        return userNameRepository.findAll();
    }

    @Override
    public UserName findById(String id) {
    	logger.info("Request for fetchAllById from userName",id);
        return userNameRepository.findById(id).get();
    }

    @Override
    public void save(UserName entity) {
    	logger.debug("Request for save from userName",entity);
        userNameRepository.save(entity);
    }

    @Override
    public void delete(String id) {
    	logger.info("Request for delete userName",id);
        userNameRepository.deleteById(id);
    }
}



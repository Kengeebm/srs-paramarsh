package com.paraamarsh.jobpost.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraamarsh.jobpost.domain.ClientName;
import com.paraamarsh.jobpost.repository.ClientNameRepository;
import com.paraamarsh.jobpost.service.ClientNameService;
import com.paraamarsh.jobpost.web.rest.ExperienceController;

@Service
public class ClientNameServiceImpl implements ClientNameService {
	private static final Logger logger = LoggerFactory.getLogger(ClientNameServiceImpl.class);

    @Autowired
    ClientNameRepository clientNameRepository;

    @Override
    public List<ClientName> findAll() {
    	logger.debug(" fetchALL from ClientName{}");
        return clientNameRepository.findAll();
    }

    @Override
    public ClientName findById(String id) {
    	logger.debug("fetchALLbyId from ClientName{}",id);
        return clientNameRepository.findById(id).get();
    }

    @Override
    public void save(ClientName entity) {
    	logger.debug("update from ClientName{}",entity);
        clientNameRepository.save(entity);
    }

    @Override
    public void delete(String id) {
    	logger.debug("request for delete ClientName",id);
    	clientNameRepository.deleteById(id);
    }
}



package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.Location;
import com.paraamarsh.jobpost.repository.LocationRepository;
import com.paraamarsh.jobpost.service.LocationService;
import com.paraamarsh.jobpost.web.rest.LocationController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
    	logger.info("Request for fetchall from Location{}");
        return locationRepository.findAll();
    }

    @Override
    public Location findById(String id) {
    	logger.info("Request for fetchall from Location{}",id);
        return locationRepository.findById(id).get();
    }

    @Override
    public void save(Location entity) {
    	logger.info("Request for save from Location{}",entity);
        locationRepository.save(entity);
    }

    @Override
    public void delete(String id) {
    	logger.info("Request for delete Location{}",id);
        locationRepository.deleteById(id);
    }
}



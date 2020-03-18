package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.Location;
import com.paraamarsh.jobpost.service.impl.LocationServiceImpl;
import com.paraamarsh.jobpost.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    LocationServiceImpl locationService;

    private static final String ENTITY_NAME = "jobLocation";

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @GetMapping(Constants.RESOURCE_LOCATION_URL)
    public List<Location> findAll() {
    	logger.info("request for fetchall from location {}");
        return locationService.findAll();
    }

    @GetMapping(Constants.RESOURCE_LOCATION_URL + "/{id}")
    public Location findById(@PathVariable String id) {
    	logger.info("request for fetchall from location {}",id);
        return locationService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_LOCATION_URL)
    public ResponseEntity<Boolean> save(@RequestBody Location location) {
        if (location.getId() == null) {
            List<Location> locationList = locationService.findAll();
            if (locationList.stream().filter(data -> data.getName().equals(location.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            locationService.save(location);
        } else {
            locationService.save(location);
        }
        logger.debug("request for save from location {}",location);
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_LOCATION_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        locationService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_LOCATION_URL)
    public ResponseEntity<Boolean> update(@RequestBody Location location) {
        if (location.getId() != null) {
            List<Location> locationList = locationService.findAll();
            if (locationList.stream().filter(data -> data.getName().equals(location.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            locationService.save(location);
        } else {
            locationService.save(location);
        }
        logger.debug("request for update from location {}",location);
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}

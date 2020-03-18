package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.JobPosition;
import com.paraamarsh.jobpost.service.impl.PositionNameServiceImpl;
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
public class PositionNameController {

    @Autowired
    PositionNameServiceImpl positionNameService;

    private static final String ENTITY_NAME = "jobPositionName";

    private static final Logger logger = LoggerFactory.getLogger(PositionNameController.class);

    @GetMapping(Constants.RESOURCE_POSITION_URL)
    public List<JobPosition> findAll() {
    	logger.info("request for fetchall JobPosition {}");
        return positionNameService.findAll();
    }

    @GetMapping(Constants.RESOURCE_POSITION_URL + "/{id}")
    public JobPosition findById(@PathVariable String id) {
    	logger.info("request for fetchallById JobPosition {}",id);
    	
        return positionNameService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_POSITION_URL)
    public ResponseEntity<Boolean> save(@RequestBody JobPosition jobPosition) {
        if (jobPosition.getId() == null) {
            List<JobPosition> jobPositionList = positionNameService.findAll();
            if (jobPositionList.stream().filter(data -> data.getName().equals(jobPosition.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            positionNameService.save(jobPosition);
        } else {
            positionNameService.save(jobPosition);
        }
        logger.debug("request for save JobPosition {}",jobPosition);
    	
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_POSITION_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        positionNameService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_POSITION_URL)
    public ResponseEntity<Boolean> update(@RequestBody JobPosition jobPosition) {
        if (jobPosition.getId() != null) {
            List<JobPosition> jobPositionList = positionNameService.findAll();
            if (jobPositionList.stream().filter(data -> data.getName().equals(jobPosition.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            positionNameService.save(jobPosition);
        } else {
            positionNameService.save(jobPosition);
        }
        logger.debug("request for update JobPosition {}",jobPosition);
    	
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}

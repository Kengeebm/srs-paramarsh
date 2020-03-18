package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.ClientName;
import com.paraamarsh.jobpost.domain.JobNo;
import com.paraamarsh.jobpost.service.impl.JobNoServiceImpl;
import com.paraamarsh.jobpost.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Predicate;


@RestController
@RequestMapping("/api")
public class JobNoController {

    @Autowired
    JobNoServiceImpl jobNoService;

    private static final String ENTITY_NAME = "jobNo";

    private static final Logger logger = LoggerFactory.getLogger(JobNoController.class);

    @GetMapping(Constants.RESOURCE_JOBNO_URL)
    public List<JobNo> findAll() {
    	logger .debug("Request for fetchall from jobNo");
        return jobNoService.findAll();
    }

    @GetMapping(Constants.RESOURCE_JOBNO_URL + "/{id}")
    public JobNo findById(@PathVariable String id) {
    	logger .debug("Request for fetchall from jobNo",id);
        return jobNoService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_JOBNO_URL)
    public ResponseEntity<Boolean> save(@RequestBody JobNo jobNo) {
        if (jobNo.getId() == null) {
            List<JobNo> jobNoList = jobNoService.findAll();
            if (jobNoList.stream().filter(data -> data.getName().equals(jobNo.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            jobNoService.save(jobNo);
        } else {
            jobNoService.save(jobNo);
        }
        logger .debug("Request for save from jobNo",jobNo); 
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_JOBNO_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        jobNoService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_JOBNO_URL)
    public ResponseEntity<Boolean> update(@RequestBody JobNo jobNo) {
        if (jobNo.getId() != null) {
            List<JobNo> jobNoList = jobNoService.findAll();
            if (jobNoList.stream().filter(data -> data.getName().equals(jobNo.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            jobNoService.save(jobNo);
        } else {
            jobNoService.save(jobNo);
        }
        logger .debug("Request for update from jobNo",jobNo); 
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}

package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.JobExperience;
import com.paraamarsh.jobpost.service.ExperienceService;
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
public class ExperienceController {

    private static final String ENTITY_NAME = "jobPostExperience";

    private static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    ExperienceService experienceService;

    @GetMapping(Constants.RESOURCE_EXPERIENCE_URL)
    public List<JobExperience> findAll() {
        return experienceService.findAll();
    }

    @GetMapping(Constants.RESOURCE_EXPERIENCE_URL + "/{id}")
    public JobExperience findById(@PathVariable String id) {
        return experienceService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_EXPERIENCE_URL)
    public ResponseEntity<Boolean> save(@RequestBody JobExperience experience) {
        if (experience.getId() == null) {
            List<JobExperience> jobExperienceList = experienceService.findAll();
            if (jobExperienceList.stream().filter(data -> data.getName().equals(experience.getName())).count()>0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            experienceService.save(experience);
        } else {
            experienceService.save(experience);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_EXPERIENCE_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        experienceService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_EXPERIENCE_URL)
    public ResponseEntity<Boolean> update(@RequestBody JobExperience experience) {
        if (experience.getId() != null) {
            List<JobExperience> jobExperienceList = experienceService.findAll();
            if (jobExperienceList.stream().filter(data -> data.getName().equals(experience.getName())).count()>0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            experienceService.save(experience);
        } else {
            experienceService.save(experience);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}

package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.UserName;
import com.paraamarsh.jobpost.service.UserNameService;
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
public class UserNameController {

    private static final String ENTITY_NAME = "jobPostUserName";

    @Autowired
    UserNameService userNameService;

    private static final Logger logger = LoggerFactory.getLogger(UserNameController.class);

    @GetMapping(Constants.RESOURCE_USER_NAME_URL)
    public List<UserName> findAll() {
    	logger.info("Request for fetchAll from userName");
        return userNameService.findAll();
    }

    @GetMapping(Constants.RESOURCE_USER_NAME_URL + "/{id}")
    public UserName findById(@PathVariable String id) {
    	logger.info("Request for fetchAll from userName",id);
        return userNameService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_USER_NAME_URL)
    public ResponseEntity<Boolean> save(@RequestBody UserName userName) {
        if (userName.getId() == null) {
            List<UserName> userNameList = userNameService.findAll();
            if (userNameList.stream().filter(data -> data.getName().equals(userName.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            userNameService.save(userName);
        } else {
            userNameService.save(userName);
        }
        logger.debug("Request for save from userName",userName); 
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_USER_NAME_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        userNameService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_USER_NAME_URL)
    public ResponseEntity<Boolean> update(@RequestBody UserName userName) {
        if (userName.getId() != null) {
            List<UserName> userNameList = userNameService.findAll();
            if (userNameList.stream().filter(data -> data.getName().equals(userName.getName())).count() > 0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            userNameService.save(userName);
        } else {
            userNameService.save(userName);
        }
        logger.debug("Request for update from userName",userName); 
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}

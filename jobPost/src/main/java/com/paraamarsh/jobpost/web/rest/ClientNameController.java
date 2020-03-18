package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.ClientName;
import com.paraamarsh.jobpost.domain.JobExperience;
import com.paraamarsh.jobpost.service.ClientNameService;
import com.paraamarsh.jobpost.service.impl.ClientNameServiceImpl;
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
public class ClientNameController {

    private static final String ENTITY_NAME = "jobPostClientName";

    @Autowired
    ClientNameService clientNameService;

    private static final Logger logger = LoggerFactory.getLogger(ClientNameController.class);

    @GetMapping(Constants.RESOURCE_CLIENT_NAME_URL)
    public List<ClientName> findAll() {
    	 logger.debug("request for fetchALL ClientName"  );
        return clientNameService.findAll();
    }

    @GetMapping(Constants.RESOURCE_CLIENT_NAME_URL + "/{id}")
    public ClientName findById(@PathVariable String id) {
    	 logger.debug("request to fetchall clientNames findById:" + id);
        return clientNameService.findById(id);
    }

    @PostMapping(Constants.RESOURCE_CLIENT_NAME_URL)
    public ResponseEntity<Boolean> save(@RequestBody ClientName experience) {
    	
        if (experience.getId() == null) {
            List<ClientName> clientNames = clientNameService.findAll();
            if (clientNames.stream().filter(data -> data.getName().equals(experience.getName())).count()>0) {
                return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
            }
            clientNameService.save(experience);
        } else {
            clientNameService.save(experience);
        }
        logger.debug("request to save clientNames {}",experience);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping(Constants.RESOURCE_CLIENT_NAME_URL + "/{id}")
    public void delete(@PathVariable String id) {
        logger.debug("request for delete id:" + id);
        if (id == null) {
            throw new BadRequestAlertException("A entry cannot have an ID", ENTITY_NAME, "id not exists");
        }
        clientNameService.delete(id);
    }

    @PutMapping(Constants.RESOURCE_CLIENT_NAME_URL)
    public ResponseEntity<Boolean> update(@RequestBody ClientName experience) {  	
      if (experience.getId() != null) {
          List<ClientName> clientNames = clientNameService.findAll();
          if (clientNames.stream().filter(data -> data.getName().equals(experience.getName())).count()>0) {
              return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
          }
          clientNameService.save(experience);
      } else {
          clientNameService.save(experience);
      }
      logger.debug("request to update clientNames {}",experience);
      return new ResponseEntity<Boolean>(true, HttpStatus.OK);
  }

}

package com.paraamarsh.jobpost.service.impl;

import java.util.List;

import com.paraamarsh.jobpost.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraamarsh.jobpost.domain.Candidate;
import com.paraamarsh.jobpost.restTemplate.ImConnection;
import com.paraamarsh.jobpost.service.JobOperationService;

@Service
public class JobOperationServiceImpl implements JobOperationService {
    private static final Logger logger = LoggerFactory.getLogger(JobOperationServiceImpl.class);


    @Autowired
    ImConnection connection;

    @Override
    public List<Candidate> getCandidatesFromIm(String query) {
        String serverUrl = AuthoritiesConstants.IM_URL + "/api";
        String searchByPosition = "/_search/candidatesposition?query=";
        logger.debug("fetchall  from connection{}", query);
        return connection.getData(serverUrl + searchByPosition + query);
    }
}

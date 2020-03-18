package com.paraamarsh.jobpost.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paraamarsh.jobpost.domain.Candidate;
import com.paraamarsh.jobpost.service.JobOperationService;

@RestController
public class JobOperationController {
	private static final Logger logger = LoggerFactory.getLogger(JobOperationController.class);

	@Autowired
	JobOperationService jobOperationService;
	
	@GetMapping("/getCandidatesRecomdation")
	public List<Candidate> getCandidatesRecomdation(String position) {
		logger.info("request for fetchall jobOperation {}",position);
		return jobOperationService.getCandidatesFromIm(position);
	}
}
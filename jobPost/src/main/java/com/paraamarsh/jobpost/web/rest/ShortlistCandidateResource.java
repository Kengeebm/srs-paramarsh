package com.paraamarsh.jobpost.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraamarsh.jobpost.domain.ShortlistCandidate;
import com.paraamarsh.jobpost.service.ShortlistCandidateService;

@RestController
@RequestMapping("/api")
public class ShortlistCandidateResource {
	private static final Logger logger = LoggerFactory.getLogger(ShortlistCandidateResource.class);

	@Autowired
	ShortlistCandidateService candidateService;
	
	@GetMapping("/shortlist/candidate")
	public ResponseEntity<List<ShortlistCandidate>> findAll() {
		logger.info("Request for fetchall from shortlistcandidate{}");
		return new ResponseEntity<List<ShortlistCandidate>>(candidateService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/shortlist/candidate/{id}")
	public ResponseEntity<Optional<ShortlistCandidate>> findById(@PathVariable String id) {
		logger.info("Request for fetchall from shortlistcandidate{}",id);
		return new ResponseEntity<Optional<ShortlistCandidate>>(candidateService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/shortlist/candidate/create")
	public ResponseEntity<ShortlistCandidate> create(@RequestBody ShortlistCandidate candidate) {
		logger.debug("Request for update from shortlistcandidate{}",candidate);
		return new ResponseEntity<ShortlistCandidate>(candidateService.create(candidate),HttpStatus.OK);
	}
	
	@PostMapping("/shortlist/candidate/createAll")
	public ResponseEntity<List<ShortlistCandidate>> createAll(@RequestBody List<ShortlistCandidate> candidates) {
		logger.debug("Request for update from shortlistcandidate{}",candidates);
		return new ResponseEntity<List<ShortlistCandidate>>(candidateService.createAll(candidates),HttpStatus.OK);
	}
	
	@PutMapping("/shortlist/candidate/update/{id}")
	public ResponseEntity<ShortlistCandidate> update(@RequestBody ShortlistCandidate candidate) {
		logger.debug("Request for save from shortlistcandidate{}",candidate);
		return new ResponseEntity<ShortlistCandidate>(candidateService.update(candidate),HttpStatus.OK);
	}
	
	@PutMapping("/shortlist/candidate/updateAll")
	public ResponseEntity<List<ShortlistCandidate>> updateAll(@RequestBody List<ShortlistCandidate> candidates) {
		logger.debug("Request for save from shortlistcandidate{}",candidates);
		return new ResponseEntity<List<ShortlistCandidate>>(candidateService.updateAll(candidates),HttpStatus.OK);
	}
	
	@DeleteMapping("/shortlist/candidate/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
	      candidateService.delete(id);
	      logger.debug("Request for delete from shortlistcandidate{}",id);
	      return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping("/shortlist/candidate/deleteAll")
	public ResponseEntity<String> deleteAll(@RequestBody List<ShortlistCandidate> candidates) {
	      candidateService.deleteAll(candidates);
	      logger.debug("Request for delete from shortlistcandidate{}",candidates);
	      return new ResponseEntity<String>(HttpStatus.OK);
	    		  
	}
	
	@GetMapping("/shortlist/candidate/countByscheduled/{scheduled}")
	public ResponseEntity<Long> countByscheduled(@PathVariable Boolean scheduled){
		System.err.println(scheduled);
		Long count =candidateService.countByscheduled(scheduled);
		logger.debug("Request for count of scheduled from shortlistcandidate{}",scheduled);
		return new ResponseEntity<Long>(count,HttpStatus.OK);
		
	}
	
	@GetMapping("/shortlist/candidate/countByshortlisted/{shortlisted}")
	public ResponseEntity<Long> countByshortlisted(@PathVariable Boolean shortlisted){
		Long count =candidateService.countByshortlisted(shortlisted);
		logger.debug("Request for count of shortlisted from shortlistcandidate{}",shortlisted);
		return new ResponseEntity<Long>(count,HttpStatus.OK);
	}
}

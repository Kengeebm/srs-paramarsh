package com.paraamarsh.jobpost.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraamarsh.jobpost.domain.ShortlistCandidate;
import com.paraamarsh.jobpost.repository.ShortlistCandidateRepository;
import com.paraamarsh.jobpost.service.ShortlistCandidateService;

@Service
public class ShortlistCandidateServiceImpl implements ShortlistCandidateService {
	private static final Logger logger = LoggerFactory.getLogger(ShortlistCandidateServiceImpl.class);


	@Autowired
	ShortlistCandidateRepository candidateRepository;
	
	@Override
	public List<ShortlistCandidate> findAll() {
		logger.info("Request for fetchall from ShortlistCandidate{}");
		return candidateRepository.findAll();
	}
	
	@Override
	public Optional<ShortlistCandidate> findById(String id) {
		logger.info("Request for fetchall from ShortlistCandidate{}",id);
		return candidateRepository.findById(id);
	}
	
	@Override
	public ShortlistCandidate create(ShortlistCandidate candidate) {
		logger.info("Request for create ShortlistCandidate{}",candidate);
	      return candidateRepository.save(candidate);
	}
	
	@Override
	public List<ShortlistCandidate> createAll(List<ShortlistCandidate> candidates) {
		logger.info("Request for createall ShortlistCandidate{}",candidates);
	      return candidateRepository.saveAll(candidates);
	}
	
	@Override
	public ShortlistCandidate update(ShortlistCandidate candidate) {
		logger.debug("Request for update ShortlistCandidate{}",candidate);
	      return candidateRepository.save(candidate);
	}
	
	@Override
	public List<ShortlistCandidate> updateAll(List<ShortlistCandidate> candidates) {
		logger.debug("Request for updateAll ShortlistCandidate{}",candidates);
	      return candidateRepository.saveAll(candidates);
	}
	
	@Override
	public void delete(Integer id) {
		logger.info("Request for delete ShortlistCandidate{}",id);
	      candidateRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll(List<ShortlistCandidate> candidates) {
		logger.info("Request for deleteAll ShortlistCandidate{}",candidates);
	      candidateRepository.deleteAll(candidates);
	}
	
	@Override
	public Long countByscheduled(Boolean scheduled) {
		return candidateRepository.countByscheduled(scheduled);
	}

	

	@Override
	public Long countByshortlisted(Boolean shortlisted) {
		// TODO Auto-generated method stub
		return candidateRepository.countByshortlisted(shortlisted);
	}
}

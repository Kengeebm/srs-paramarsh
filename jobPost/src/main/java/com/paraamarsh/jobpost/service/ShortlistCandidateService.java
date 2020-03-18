package com.paraamarsh.jobpost.service;

import java.util.List;
import java.util.Optional;

import com.paraamarsh.jobpost.domain.ShortlistCandidate;

public interface ShortlistCandidateService {

	List<ShortlistCandidate> findAll();

	Optional<ShortlistCandidate> findById(String id);

	ShortlistCandidate create(ShortlistCandidate candidate);

	List<ShortlistCandidate> createAll(List<ShortlistCandidate> candidates);

	ShortlistCandidate update(ShortlistCandidate candidate);

	List<ShortlistCandidate> updateAll(List<ShortlistCandidate> candidates);

	void delete(Integer id);

	void deleteAll(List<ShortlistCandidate> candidates);
	
	Long countByscheduled(Boolean scheduled);

	Long countByshortlisted(Boolean shortlisted);

}

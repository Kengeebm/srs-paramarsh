package com.paraamarsh.jobpost.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paraamarsh.jobpost.domain.ShortlistCandidate;

@Repository
public interface ShortlistCandidateRepository extends MongoRepository<ShortlistCandidate, Integer> {

	Long countByscheduled(Boolean scheduled);
	
	Long countByshortlisted(Boolean shortlisted);

	Optional<ShortlistCandidate> findById(String id);
}

package com.paraamarsh.jobpost.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paraamarsh.jobpost.domain.Job;

@Repository
public interface DashBoardRepository extends MongoRepository<Job, String> {

	Long countByClosedFlag(Boolean closedFlag);
	
	Long countByAssignee(List<String> assignee);
}


package com.paraamarsh.jobpost.repository;

import com.paraamarsh.jobpost.domain.JobPosition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends MongoRepository<JobPosition, String> {

}

package com.paraamarsh.jobpost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paraamarsh.jobpost.domain.JobExperience;

@Repository
public interface ExperienceRepository extends MongoRepository<JobExperience, String> {

}

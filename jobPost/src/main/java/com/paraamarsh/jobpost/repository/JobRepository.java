package com.paraamarsh.jobpost.repository;

import com.paraamarsh.jobpost.domain.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data MongoDB repository for the Job entity.
 */
@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findAllByClientName(String clientName);

    List<Job> findAllByLocation(String location);

    List<Job> findAllByPositionName(String position);
}

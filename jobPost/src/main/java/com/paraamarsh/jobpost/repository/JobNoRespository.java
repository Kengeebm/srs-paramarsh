package com.paraamarsh.jobpost.repository;

import com.paraamarsh.jobpost.domain.JobNo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobNoRespository extends MongoRepository<JobNo, String> {

}

package com.paraamarsh.jobpost.service;

import java.util.List;

import com.paraamarsh.jobpost.domain.JobExperience;
import com.paraamarsh.jobpost.domain.JobNo;
import com.paraamarsh.jobpost.domain.Location;

public interface JobNoService {

    List<JobNo> findAll();

    JobNo findById(String id);

    void save(JobNo entity);

    void delete(String id);

}

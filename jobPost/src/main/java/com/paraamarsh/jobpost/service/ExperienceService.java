package com.paraamarsh.jobpost.service;

import java.util.List;

import com.paraamarsh.jobpost.domain.JobExperience;

public interface ExperienceService {

	List<JobExperience> findAll();

    JobExperience findById(String id);

	void save(JobExperience entity);

	void delete(String id);

}

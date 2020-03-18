package com.paraamarsh.jobpost.service;

import com.paraamarsh.jobpost.domain.JobPosition;

import java.util.List;

public interface PositionNameService {

    List<JobPosition> findAll();

    JobPosition findById(String id);

    void save(JobPosition entity);

    void delete(String id);
}

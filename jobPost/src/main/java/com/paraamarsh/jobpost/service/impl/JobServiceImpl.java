package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.domain.Job;
import com.paraamarsh.jobpost.repository.JobRepository;
import com.paraamarsh.jobpost.security.SecurityUtils;
import com.paraamarsh.jobpost.service.JobHistoryService;
import com.paraamarsh.jobpost.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Job}.
 */
@Service
public class JobServiceImpl implements JobService {

    private final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private JobHistoryService historyService;

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Job save(Job job) {
        Job jb = null;
        logger.debug("Request to save Job : {}", job);
        try {
            jb = jobRepository.save(job);
            historyService.mapJobToJobHistory(job);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return jb;
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<Job> findAll(Pageable pageable) {
    	logger.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable);
    }


    /**
     * Get one job by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Job> findOne(String id) {
    	logger.debug("Request to get Job : {}", id);
        return jobRepository.findById(id);
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
    	logger.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
    }

    /**
     * Get total number of records from database
     *
     * @return record count
     */
    @Override
    public Long recordCount() {
    	logger.debug("Request to get record count : {}");
        return jobRepository.count();
    }

    @Override
    public Boolean closeJob(String id) {
        Optional<Job> job = jobRepository.findById(id);
        try {
            job.get().setClosedOn(LocalDateTime.now());
            job.get().setClosedFlag(true);
            job.get().setClosedBy(SecurityUtils.getCurrentUserLogin().get());
            jobRepository.save(job.get());
            return true;
        } catch (Exception e) {
        	 logger.error("An exception occurred.", e);
            return false;
        }
    }

    @Override
    public List<Job> findAllByClientName(String clientName) {
    	logger.debug("Request to get job: {}",clientName);
        return jobRepository.findAllByClientName(clientName);
    }

    @Override
    public List<Job> findAllByLocation(String location) {
    	logger.debug("Request to get job: {}",location);
        return jobRepository.findAllByLocation(location);
    }

    @Override
    public List<Job> findAllByJobPosition(String position) {
    	logger.debug("Request to get job: {}",position);
        return jobRepository.findAllByPositionName(position);
    }
}

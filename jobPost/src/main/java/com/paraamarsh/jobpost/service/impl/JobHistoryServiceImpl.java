package com.paraamarsh.jobpost.service.impl;

import com.paraamarsh.jobpost.service.JobHistoryService;
import com.paraamarsh.jobpost.domain.Job;
import com.paraamarsh.jobpost.domain.JobHistory;
import com.paraamarsh.jobpost.repository.JobHistoryRepository;
import com.paraamarsh.jobpost.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing {@link JobHistory}.
 */
@Service
public class JobHistoryServiceImpl implements JobHistoryService {

    private final Logger Logger = LoggerFactory.getLogger(JobHistoryServiceImpl.class);

    private final JobHistoryRepository jobHistoryRepository;

    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    /**
     * Save a jobHistory.
     *
     * @param jobHistory the entity to save.
     * @return the persisted entity.
     */
    @Override
    public JobHistory save(JobHistory jobHistory) {
        Logger.debug("Request to save JobHistory : {}",jobHistory);
        return jobHistoryRepository.save(jobHistory);
    }

    /**
     * Get all the jobHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<JobHistory> findAll(Pageable pageable) {
    	 Logger.debug("Request to get all JobHistories");
        return jobHistoryRepository.findAll(pageable);
    }


    /**
     * Get one jobHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<JobHistory> findOne(String id) {
    	 Logger.debug("Request to get JobHistory : {}", id);
        return jobHistoryRepository.findById(id);
    }

    /**
     * Delete the jobHistory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
    	 Logger.debug("Request to delete JobHistory : {}", id);
        jobHistoryRepository.deleteById(id);
    }
    
    @Override
    public void mapJobToJobHistory(Job job) {
    	JobHistory history = new JobHistory().clientName(job.getClientName())
    			.closedBy(job.getClosedBy()).closedOn(job.getClosedOn())
    			.comments(job.getComments()).endDate(job.getEndDate()).fromExp(job.getFromExp()).toExp(job.getToExp())
    			.filledPosition(job.getFilledPosition()).jobDesc(job.getJobDesc()).jobId(job.getJobId())
    			.location(job.getLocation()).noOfPosition(job.getNoOfPosition()).openedBy(job.getOpenedBy())
    			.positionName(job.getPositionName()).startDate(job.getStartDate()).updatedBy(SecurityUtils.getCurrentUserLogin().toString())
    			.updatedDate(LocalDate.now()).assignee(job.getAssignee()).closedFlag(job.getClosedFlag());
    		save(history);
    }
}

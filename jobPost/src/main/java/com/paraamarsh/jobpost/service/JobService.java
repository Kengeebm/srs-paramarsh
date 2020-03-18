package com.paraamarsh.jobpost.service;

import com.paraamarsh.jobpost.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Job}.
 */
public interface JobService {

    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    Job save(Job job);

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Job> findAll(Pageable pageable);


    /**
     * Get the "id" job.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Job> findOne(String id);

    /**
     * Delete the "id" job.
     *
     * @param id the id of the entity.
     */
    void delete(String id);


    /**
     * Get total number of records count
     *
     * @return long
     */
    Long recordCount();


    /**
     * closing the open job
     *
     * @param id the id
     * @return boolean
     */
    Boolean closeJob(String id);


    /**
     * Find all by client name list.
     *
     * @param query the query
     * @return the list
     */
    List<Job> findAllByClientName(String query);

    /**
     * Find all by location list.
     *
     * @param query the query
     * @return the list
     */
    List<Job> findAllByLocation(String query);

    /**
     * Find all by job position list.
     *
     * @param query the query
     * @return the list
     */
    List<Job> findAllByJobPosition(String query);
}

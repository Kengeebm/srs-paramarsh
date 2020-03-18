package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.domain.Candidate;
import com.paraamarsh.jobpost.domain.Job;
import com.paraamarsh.jobpost.service.JobOperationService;
import com.paraamarsh.jobpost.service.JobService;
import com.paraamarsh.jobpost.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.paraamarsh.jobpost.domain.Job}.
 */
@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger logger = LoggerFactory.getLogger(JobResource.class);

    private static final String ENTITY_NAME = "jobPostJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobService jobService;

    @Autowired
    private JobOperationService jobOperationService;


    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * {@code POST  /jobs} : Create a new job.
     *
     * @param job the job to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new job, or with status {@code 400 (Bad Request)} if the job has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) throws URISyntaxException {
        logger.debug("REST request to save Job : {}", job);
        if (job.getId() != null) {
            throw new BadRequestAlertException("A new job cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Job result = jobService.save(job);
        return ResponseEntity.created(new URI("/api/jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jobs} : Updates an existing job.
     *
     * @param job the job to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated job,
     * or with status {@code 400 (Bad Request)} if the job is not valid,
     * or with status {@code 500 (Internal Server Error)} if the job couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jobs")
    public ResponseEntity<Job> updateJob(@Valid @RequestBody Job job) throws URISyntaxException {
        logger.debug("REST request to update Job : {}", job);
        if (job.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Job result = jobService.save(job);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, job.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /jobs} : get all the jobs.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobs in body.
     */
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        logger.debug("REST request to get a page of Jobs");
        Page<Job> page = jobService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jobs/:id} : get the "id" job.
     *
     * @param id the id of the job to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the job, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        logger.debug("REST request to get Job : {}",id);
        Optional<Job> job = jobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(job);
    }

    /**
     * {@code DELETE  /jobs/:id} : delete the "id" job.
     *
     * @param id the id of the job to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        logger.debug("REST request to delete Job :{}",id);
        jobService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    /**
     * Get total number of records count
     *
     * @return record count
     */
    @GetMapping("/recordcount")
    public ResponseEntity<Long> recordCount() {
        Long record = jobService.recordCount();
        logger.debug("REST request to get recordcount : {}",record);
        return new ResponseEntity<Long>(record, HttpStatus.CREATED);
    }

    @GetMapping("/closejob/{id}")
    public ResponseEntity<Boolean> closeJob(@PathVariable String id) {
        Boolean closeStatus = jobService.closeJob(id);
        logger.debug("REST request to get closestatus : {}",id);
       return new ResponseEntity<Boolean>(closeStatus, HttpStatus.OK);
    }

    @GetMapping("/_search/candidatesposition")
    public ResponseEntity<List<Candidate>> search(@RequestParam String query) {
    	 logger.debug("REST request to get candidatesposition : {}",query);
        return new ResponseEntity<List<Candidate>>(jobOperationService.getCandidatesFromIm(query), HttpStatus.OK);
    }


    @GetMapping("/_search/clientname/{query}")
    public ResponseEntity<List<Job>> findByClientName(@PathVariable String query) {
    	 logger.debug("REST request to get clientname: {}",query);
    	 return new ResponseEntity<List<Job>>(jobService.findAllByClientName(query), HttpStatus.OK);
    }

    @GetMapping("/_search/location/{query}")
    public ResponseEntity<List<Job>> findByLocation(@PathVariable String query) {
    	logger.debug("REST request to get location: {}",query);
        return new ResponseEntity<List<Job>>(jobService.findAllByLocation(query), HttpStatus.OK);
    }

    @GetMapping("/_search/jobposition/{query}")
    public ResponseEntity<List<Job>> findByJobPosition(@PathVariable String query) {
    	logger.debug("REST request to get jobposition : {}",query);
        return new ResponseEntity<List<Job>>(jobService.findAllByJobPosition(query), HttpStatus.OK);
    }

}

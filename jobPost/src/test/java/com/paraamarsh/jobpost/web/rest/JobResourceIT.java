package com.paraamarsh.jobpost.web.rest;

import com.paraamarsh.jobpost.JobPostApp;
import com.paraamarsh.jobpost.config.SecurityBeanOverrideConfiguration;
import com.paraamarsh.jobpost.domain.Job;
import com.paraamarsh.jobpost.repository.JobRepository;
import com.paraamarsh.jobpost.service.JobService;
import com.paraamarsh.jobpost.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.paraamarsh.jobpost.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link JobResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, JobPostApp.class})
public class JobResourceIT {

    private static final String DEFAULT_JOB_ID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_POSITION = 1;
    private static final Integer UPDATED_NO_OF_POSITION = 2;

    private static final String DEFAULT_POSITION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_DESC = "AAAAAAAAAA";
    private static final String UPDATED_JOB_DESC = "BBBBBBBBBB";

    private static final Double DEFAULT_FROM_EXP_REQ = 0.0;
    private static final Double UPDATED_FROM_EXP_REQ = 1.0;
    private static final Double DEFAULT_TO_EXP_REQ = 0.0;
    private static final Double UPDATED_TO_EXP_REQ = 2.0;
    
    private static final Integer DEFAULT_FILLED_POSITION = 1;
    private static final Integer UPDATED_FILLED_POSITION = 2;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDateTime DEFAULT_CLOSED_ON = LocalDateTime.now(ZoneId.systemDefault());
    private static final LocalDateTime UPDATED_CLOSED_ON = LocalDateTime.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPENED_BY = "AAAAAAAAAA";
    private static final String UPDATED_OPENED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CLOSED_BY = "BBBBBBBBBB";

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restJobMockMvc;

    private Job job;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobResource jobResource = new JobResource(jobService);
        this.restJobMockMvc = MockMvcBuilders.standaloneSetup(jobResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Job createEntity() {
        Job job = new Job()
            .jobId(DEFAULT_JOB_ID)
            .clientName(DEFAULT_CLIENT_NAME)
            .noOfPosition(DEFAULT_NO_OF_POSITION)
            .positionName(DEFAULT_POSITION_NAME)
            .location(DEFAULT_LOCATION)
            .jobDesc(DEFAULT_JOB_DESC)
            .FromExp(DEFAULT_FROM_EXP_REQ).ToExp(DEFAULT_TO_EXP_REQ)
            .filledPosition(DEFAULT_FILLED_POSITION)
            .comments(DEFAULT_COMMENTS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .closedOn(DEFAULT_CLOSED_ON)
            .openedBy(DEFAULT_OPENED_BY)
            .closedBy(DEFAULT_CLOSED_BY);
        return job;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Job createUpdatedEntity() {
        Job job = new Job()
            .jobId(UPDATED_JOB_ID)
            .clientName(UPDATED_CLIENT_NAME)
            .noOfPosition(UPDATED_NO_OF_POSITION)
            .positionName(UPDATED_POSITION_NAME)
            .location(UPDATED_LOCATION)
            .jobDesc(UPDATED_JOB_DESC)
            .FromExp(DEFAULT_FROM_EXP_REQ).ToExp(DEFAULT_TO_EXP_REQ)
            .filledPosition(UPDATED_FILLED_POSITION)
            .comments(UPDATED_COMMENTS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .closedOn(UPDATED_CLOSED_ON)
            .openedBy(UPDATED_OPENED_BY)
            .closedBy(UPDATED_CLOSED_BY);
        return job;
    }

    @BeforeEach
    public void initTest() {
        jobRepository.deleteAll();
        job = createEntity();
    }

    @Test
    public void createJob() throws Exception {
        int databaseSizeBeforeCreate = jobRepository.findAll().size();

        // Create the Job
        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isCreated());

        // Validate the Job in the database
        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeCreate + 1);
        Job testJob = jobList.get(jobList.size() - 1);
        assertThat(testJob.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(testJob.getClientName()).isEqualTo(DEFAULT_CLIENT_NAME);
        assertThat(testJob.getNoOfPosition()).isEqualTo(DEFAULT_NO_OF_POSITION);
        assertThat(testJob.getPositionName()).isEqualTo(DEFAULT_POSITION_NAME);
        assertThat(testJob.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testJob.getJobDesc()).isEqualTo(DEFAULT_JOB_DESC);
        assertThat(testJob.getFromExp()).isEqualTo(DEFAULT_FROM_EXP_REQ);
        assertThat(testJob.getToExp()).isEqualTo(DEFAULT_TO_EXP_REQ);

        assertThat(testJob.getFilledPosition()).isEqualTo(DEFAULT_FILLED_POSITION);
        assertThat(testJob.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testJob.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testJob.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testJob.getClosedOn()).isEqualTo(DEFAULT_CLOSED_ON);
        assertThat(testJob.getOpenedBy()).isEqualTo(DEFAULT_OPENED_BY);
        assertThat(testJob.getClosedBy()).isEqualTo(DEFAULT_CLOSED_BY);
    }

    @Test
    public void createJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobRepository.findAll().size();

        // Create the Job with an existing ID
        job.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkClientNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobRepository.findAll().size();
        // set the field null
        job.setClientName(null);

        // Create the Job, which fails.

        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPositionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobRepository.findAll().size();
        // set the field null
        job.setPositionName(null);

        // Create the Job, which fails.

        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobRepository.findAll().size();
        // set the field null
        job.setLocation(null);

        // Create the Job, which fails.

        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkJobDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobRepository.findAll().size();
        // set the field null
        job.setJobDesc(null);

        // Create the Job, which fails.

        restJobMockMvc.perform(post("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllJobs() throws Exception {
        // Initialize the database
        jobRepository.save(job);

        // Get all the jobList
        restJobMockMvc.perform(get("/api/jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job.getId())))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID.toString())))
            .andExpect(jsonPath("$.[*].clientName").value(hasItem(DEFAULT_CLIENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].noOfPosition").value(hasItem(DEFAULT_NO_OF_POSITION)))
            .andExpect(jsonPath("$.[*].positionName").value(hasItem(DEFAULT_POSITION_NAME.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].jobDesc").value(hasItem(DEFAULT_JOB_DESC.toString())))
            .andExpect(jsonPath("$.[*].fromExp").value(hasItem(DEFAULT_FROM_EXP_REQ)))
            .andExpect(jsonPath("$.[*].toExp").value(hasItem(DEFAULT_TO_EXP_REQ)))

            .andExpect(jsonPath("$.[*].filledPosition").value(hasItem(DEFAULT_FILLED_POSITION)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedOn").value(hasItem(DEFAULT_CLOSED_ON.toString())))
            .andExpect(jsonPath("$.[*].openedBy").value(hasItem(DEFAULT_OPENED_BY.toString())))
            .andExpect(jsonPath("$.[*].closedBy").value(hasItem(DEFAULT_CLOSED_BY.toString())));
    }
    
//    @Test
//    public void getJob() throws Exception {
//        // Initialize the database
//        jobRepository.save(job);
//
//        // Get the job
//        restJobMockMvc.perform(get("/api/jobs/{id}", job.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(job.getId()))
//            .andExpect(jsonPath("$.jobId").value(DEFAULT_JOB_ID.toString()))
//            .andExpect(jsonPath("$.clientName").value(DEFAULT_CLIENT_NAME.toString()))
//            .andExpect(jsonPath("$.noOfPosition").value(DEFAULT_NO_OF_POSITION))
//            .andExpect(jsonPath("$.positionName").value(DEFAULT_POSITION_NAME.toString()))
//            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
//            .andExpect(jsonPath("$.jobDesc").value(DEFAULT_JOB_DESC.toString()))
//            .andExpect(jsonPath("$.[*].fromExp").value(hasItem(DEFAULT_FROM_EXP_REQ)))
//            .andExpect(jsonPath("$.[*].toExp").value(hasItem(DEFAULT_TO_EXP_REQ)))
//            .andExpect(jsonPath("$.filledPosition").value(DEFAULT_FILLED_POSITION))
//            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
//            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
//            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
//            .andExpect(jsonPath("$.closedOn").value(DEFAULT_CLOSED_ON.toString()))
//            .andExpect(jsonPath("$.openedBy").value(DEFAULT_OPENED_BY.toString()))
//            .andExpect(jsonPath("$.closedBy").value(DEFAULT_CLOSED_BY.toString()));
//    }

    @Test
    public void getNonExistingJob() throws Exception {
        // Get the job
        restJobMockMvc.perform(get("/api/jobs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateJob() throws Exception {
        // Initialize the database
        jobService.save(job);

        int databaseSizeBeforeUpdate = jobRepository.findAll().size();

        // Update the job
        Job updatedJob = jobRepository.findById(job.getId()).get();
        updatedJob
            .jobId(UPDATED_JOB_ID)
            .clientName(UPDATED_CLIENT_NAME)
            .noOfPosition(UPDATED_NO_OF_POSITION)
            .positionName(UPDATED_POSITION_NAME)
            .location(UPDATED_LOCATION)
            .jobDesc(UPDATED_JOB_DESC)
            .FromExp(UPDATED_FROM_EXP_REQ).ToExp(UPDATED_TO_EXP_REQ)
            .filledPosition(UPDATED_FILLED_POSITION)
            .comments(UPDATED_COMMENTS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .closedOn(UPDATED_CLOSED_ON)
            .openedBy(UPDATED_OPENED_BY)
            .closedBy(UPDATED_CLOSED_BY);

        restJobMockMvc.perform(put("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJob)))
            .andExpect(status().isOk());

        // Validate the Job in the database
        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeUpdate);
        Job testJob = jobList.get(jobList.size() - 1);
        assertThat(testJob.getJobId()).isEqualTo(UPDATED_JOB_ID);
        assertThat(testJob.getClientName()).isEqualTo(UPDATED_CLIENT_NAME);
        assertThat(testJob.getNoOfPosition()).isEqualTo(UPDATED_NO_OF_POSITION);
        assertThat(testJob.getPositionName()).isEqualTo(UPDATED_POSITION_NAME);
        assertThat(testJob.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testJob.getJobDesc()).isEqualTo(UPDATED_JOB_DESC);
        assertThat(testJob.getFromExp()).isEqualTo(UPDATED_FROM_EXP_REQ);
        assertThat(testJob.getToExp()).isEqualTo(UPDATED_TO_EXP_REQ);

        assertThat(testJob.getFilledPosition()).isEqualTo(UPDATED_FILLED_POSITION);
        assertThat(testJob.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testJob.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testJob.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testJob.getClosedOn()).isEqualTo(UPDATED_CLOSED_ON);
        assertThat(testJob.getOpenedBy()).isEqualTo(UPDATED_OPENED_BY);
        assertThat(testJob.getClosedBy()).isEqualTo(UPDATED_CLOSED_BY);
    }

    @Test
    public void updateNonExistingJob() throws Exception {
        int databaseSizeBeforeUpdate = jobRepository.findAll().size();

        // Create the Job

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobMockMvc.perform(put("/api/jobs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(job)))
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteJob() throws Exception {
        // Initialize the database
        jobService.save(job);

        int databaseSizeBeforeDelete = jobRepository.findAll().size();

        // Delete the job
        restJobMockMvc.perform(delete("/api/jobs/{id}", job.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Job> jobList = jobRepository.findAll();
        assertThat(jobList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Job.class);
        Job job1 = new Job();
        job1.setId("id1");
        Job job2 = new Job();
        job2.setId(job1.getId());
        assertThat(job1).isEqualTo(job2);
        job2.setId("id2");
        assertThat(job1).isNotEqualTo(job2);
        job1.setId(null);
        assertThat(job1).isNotEqualTo(job2);
    }
}

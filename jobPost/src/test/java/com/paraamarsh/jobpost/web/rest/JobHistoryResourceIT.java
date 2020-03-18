package com.paraamarsh.jobpost.web.rest;

import static com.paraamarsh.jobpost.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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

import com.paraamarsh.jobpost.JobPostApp;
import com.paraamarsh.jobpost.config.SecurityBeanOverrideConfiguration;
import com.paraamarsh.jobpost.domain.JobHistory;
import com.paraamarsh.jobpost.repository.JobHistoryRepository;
import com.paraamarsh.jobpost.service.JobHistoryService;
import com.paraamarsh.jobpost.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link JobHistoryResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, JobPostApp.class})
public class JobHistoryResourceIT {

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

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private JobHistoryService jobHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restJobHistoryMockMvc;

    private JobHistory jobHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobHistoryResource jobHistoryResource = new JobHistoryResource(jobHistoryService);
        this.restJobHistoryMockMvc = MockMvcBuilders.standaloneSetup(jobHistoryResource)
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
    public static JobHistory createEntity() {
        JobHistory jobHistory = new JobHistory()
            .jobId(DEFAULT_JOB_ID)
            .clientName(DEFAULT_CLIENT_NAME)
            .noOfPosition(DEFAULT_NO_OF_POSITION)
            .positionName(DEFAULT_POSITION_NAME)
            .location(DEFAULT_LOCATION)
            .jobDesc(DEFAULT_JOB_DESC)
            .fromExp(DEFAULT_FROM_EXP_REQ).toExp(DEFAULT_TO_EXP_REQ)
            .filledPosition(DEFAULT_FILLED_POSITION)
            .comments(DEFAULT_COMMENTS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .closedOn(DEFAULT_CLOSED_ON)
            .openedBy(DEFAULT_OPENED_BY)
            .closedBy(DEFAULT_CLOSED_BY)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return jobHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobHistory createUpdatedEntity() {
        JobHistory jobHistory = new JobHistory()
            .jobId(UPDATED_JOB_ID)
            .clientName(UPDATED_CLIENT_NAME)
            .noOfPosition(UPDATED_NO_OF_POSITION)
            .positionName(UPDATED_POSITION_NAME)
            .location(UPDATED_LOCATION)
            .jobDesc(UPDATED_JOB_DESC)
            .fromExp(DEFAULT_FROM_EXP_REQ).toExp(DEFAULT_TO_EXP_REQ)
            .filledPosition(UPDATED_FILLED_POSITION)
            .comments(UPDATED_COMMENTS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .closedOn(UPDATED_CLOSED_ON)
            .openedBy(UPDATED_OPENED_BY)
            .closedBy(UPDATED_CLOSED_BY)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);
        return jobHistory;
    }

    @BeforeEach
    public void initTest() {
        jobHistoryRepository.deleteAll();
        jobHistory = createEntity();
    }

    @Test
    public void createJobHistory() throws Exception {
        int databaseSizeBeforeCreate = jobHistoryRepository.findAll().size();

        // Create the JobHistory
        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isCreated());

        // Validate the JobHistory in the database
        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        JobHistory testJobHistory = jobHistoryList.get(jobHistoryList.size() - 1);
        assertThat(testJobHistory.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(testJobHistory.getClientName()).isEqualTo(DEFAULT_CLIENT_NAME);
        assertThat(testJobHistory.getNoOfPosition()).isEqualTo(DEFAULT_NO_OF_POSITION);
        assertThat(testJobHistory.getPositionName()).isEqualTo(DEFAULT_POSITION_NAME);
        assertThat(testJobHistory.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testJobHistory.getJobDesc()).isEqualTo(DEFAULT_JOB_DESC);
        assertThat(testJobHistory.getFromExp()).isEqualTo(DEFAULT_FROM_EXP_REQ);
        assertThat(testJobHistory.getToExp()).isEqualTo(DEFAULT_TO_EXP_REQ);
        assertThat(testJobHistory.getFilledPosition()).isEqualTo(DEFAULT_FILLED_POSITION);
        assertThat(testJobHistory.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testJobHistory.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testJobHistory.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testJobHistory.getClosedOn()).isEqualTo(DEFAULT_CLOSED_ON);
        assertThat(testJobHistory.getOpenedBy()).isEqualTo(DEFAULT_OPENED_BY);
        assertThat(testJobHistory.getClosedBy()).isEqualTo(DEFAULT_CLOSED_BY);
        assertThat(testJobHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testJobHistory.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    public void createJobHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobHistoryRepository.findAll().size();

        // Create the JobHistory with an existing ID
        jobHistory.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        // Validate the JobHistory in the database
        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkClientNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobHistoryRepository.findAll().size();
        // set the field null
        jobHistory.setClientName(null);

        // Create the JobHistory, which fails.

        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPositionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobHistoryRepository.findAll().size();
        // set the field null
        jobHistory.setPositionName(null);

        // Create the JobHistory, which fails.

        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobHistoryRepository.findAll().size();
        // set the field null
        jobHistory.setLocation(null);

        // Create the JobHistory, which fails.

        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkJobDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobHistoryRepository.findAll().size();
        // set the field null
        jobHistory.setJobDesc(null);

        // Create the JobHistory, which fails.

        restJobHistoryMockMvc.perform(post("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllJobHistories() throws Exception {
        // Initialize the database
        jobHistoryRepository.save(jobHistory);

        // Get all the jobHistoryList
        restJobHistoryMockMvc.perform(get("/api/job-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobHistory.getId())))
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
            .andExpect(jsonPath("$.[*].closedBy").value(hasItem(DEFAULT_CLOSED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }
    
//    @Test
//    public void getJobHistory() throws Exception {
//        // Initialize the database
//        jobHistoryRepository.save(jobHistory);
//
//        // Get the jobHistory
//        restJobHistoryMockMvc.perform(get("/api/job-histories/{id}", jobHistory.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(jobHistory.getId()))
//            .andExpect(jsonPath("$.jobId").value(DEFAULT_JOB_ID.toString()))
//            .andExpect(jsonPath("$.clientName").value(DEFAULT_CLIENT_NAME.toString()))
//            .andExpect(jsonPath("$.noOfPosition").value(DEFAULT_NO_OF_POSITION))
//            .andExpect(jsonPath("$.positionName").value(DEFAULT_POSITION_NAME.toString()))
//            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
//            .andExpect(jsonPath("$.jobDesc").value(DEFAULT_JOB_DESC.toString()))
//            .andExpect(jsonPath("$.FromExp").value(DEFAULT_FROM_EXP_REQ))
//            .andExpect(jsonPath("$.toExp").value(DEFAULT_TO_EXP_REQ))
//
//            .andExpect(jsonPath("$.filledPosition").value(DEFAULT_FILLED_POSITION))
//            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
//            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
//            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
//            .andExpect(jsonPath("$.closedOn").value(DEFAULT_CLOSED_ON.toString()))
//            .andExpect(jsonPath("$.openedBy").value(DEFAULT_OPENED_BY.toString()))
//            .andExpect(jsonPath("$.closedBy").value(DEFAULT_CLOSED_BY.toString()))
//            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
//            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
//    }

    @Test
    public void getNonExistingJobHistory() throws Exception {
        // Get the jobHistory
        restJobHistoryMockMvc.perform(get("/api/job-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateJobHistory() throws Exception {
        // Initialize the database
        jobHistoryService.save(jobHistory);

        int databaseSizeBeforeUpdate = jobHistoryRepository.findAll().size();

        // Update the jobHistory
        JobHistory updatedJobHistory = jobHistoryRepository.findById(jobHistory.getId()).get();
        updatedJobHistory
            .jobId(UPDATED_JOB_ID)
            .clientName(UPDATED_CLIENT_NAME)
            .noOfPosition(UPDATED_NO_OF_POSITION)
            .positionName(UPDATED_POSITION_NAME)
            .location(UPDATED_LOCATION)
            .jobDesc(UPDATED_JOB_DESC)
            .fromExp(UPDATED_FROM_EXP_REQ)
            .toExp(UPDATED_TO_EXP_REQ)
            .filledPosition(UPDATED_FILLED_POSITION)
            .comments(UPDATED_COMMENTS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .closedOn(UPDATED_CLOSED_ON)
            .openedBy(UPDATED_OPENED_BY)
            .closedBy(UPDATED_CLOSED_BY)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);

        restJobHistoryMockMvc.perform(put("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJobHistory)))
            .andExpect(status().isOk());

        // Validate the JobHistory in the database
        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeUpdate);
        JobHistory testJobHistory = jobHistoryList.get(jobHistoryList.size() - 1);
        assertThat(testJobHistory.getJobId()).isEqualTo(UPDATED_JOB_ID);
        assertThat(testJobHistory.getClientName()).isEqualTo(UPDATED_CLIENT_NAME);
        assertThat(testJobHistory.getNoOfPosition()).isEqualTo(UPDATED_NO_OF_POSITION);
        assertThat(testJobHistory.getPositionName()).isEqualTo(UPDATED_POSITION_NAME);
        assertThat(testJobHistory.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testJobHistory.getJobDesc()).isEqualTo(UPDATED_JOB_DESC);
        assertThat(testJobHistory.getFromExp()).isEqualTo(UPDATED_FROM_EXP_REQ);
        assertThat(testJobHistory.getToExp()).isEqualTo(UPDATED_TO_EXP_REQ);

        assertThat(testJobHistory.getFilledPosition()).isEqualTo(UPDATED_FILLED_POSITION);
        assertThat(testJobHistory.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testJobHistory.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testJobHistory.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testJobHistory.getClosedOn()).isEqualTo(UPDATED_CLOSED_ON);
        assertThat(testJobHistory.getOpenedBy()).isEqualTo(UPDATED_OPENED_BY);
        assertThat(testJobHistory.getClosedBy()).isEqualTo(UPDATED_CLOSED_BY);
        assertThat(testJobHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testJobHistory.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    public void updateNonExistingJobHistory() throws Exception {
        int databaseSizeBeforeUpdate = jobHistoryRepository.findAll().size();

        // Create the JobHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobHistoryMockMvc.perform(put("/api/job-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobHistory)))
            .andExpect(status().isBadRequest());

        // Validate the JobHistory in the database
        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteJobHistory() throws Exception {
        // Initialize the database
        jobHistoryService.save(jobHistory);

        int databaseSizeBeforeDelete = jobHistoryRepository.findAll().size();

        // Delete the jobHistory
        restJobHistoryMockMvc.perform(delete("/api/job-histories/{id}", jobHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JobHistory> jobHistoryList = jobHistoryRepository.findAll();
        assertThat(jobHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobHistory.class);
        JobHistory jobHistory1 = new JobHistory();
        jobHistory1.setId("id1");
        JobHistory jobHistory2 = new JobHistory();
        jobHistory2.setId(jobHistory1.getId());
        assertThat(jobHistory1).isEqualTo(jobHistory2);
        jobHistory2.setId("id2");
        assertThat(jobHistory1).isNotEqualTo(jobHistory2);
        jobHistory1.setId(null);
        assertThat(jobHistory1).isNotEqualTo(jobHistory2);
    }
}

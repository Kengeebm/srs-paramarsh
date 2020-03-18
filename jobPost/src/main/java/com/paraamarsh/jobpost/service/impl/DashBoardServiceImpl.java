package com.paraamarsh.jobpost.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraamarsh.jobpost.domain.Job;
import com.paraamarsh.jobpost.domain.OpenVancancies;
import com.paraamarsh.jobpost.repository.DashBoardRepository;
import com.paraamarsh.jobpost.repository.JobRepository;
import com.paraamarsh.jobpost.repository.ShortlistCandidateRepository;
import com.paraamarsh.jobpost.service.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService {
	private static final Logger logger = LoggerFactory.getLogger(DashBoardServiceImpl.class);

	@Autowired
	DashBoardRepository boardRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Long countByClosedFlag() {
		logger.debug(" fetchALL from boardRepository{}");
		return boardRepository.countByClosedFlag(false);
	}

	@Override
	public Long countByClosedFlagClose() {
		logger.debug(" fetchALL from boardRepository{}");
	 return boardRepository.countByClosedFlag(true);
	}
	
	 /**
     *  Get total number of records from database
     *
     *@return record count
     */
    @Override
    public Long recordCount() {
    	return boardRepository.count();
    }
    
    @Override
    public Long countByAssignee(List<String> assignee) {
    	logger.debug(" update from boardRepository{}",assignee);
    	return boardRepository.countByAssignee(assignee);
    }
    @Override
   public List<OpenVancancies> openVacancies() {
    	
    	List<Job> jobs =jobRepository.findAll();
    	List<OpenVancancies> ovsList = new ArrayList<OpenVancancies>();
    	
    	Map<String, Integer> jobsMap = jobs.stream().collect(
    			Collectors.groupingBy(
    					Job::getPositionName,
    					Collectors.summingInt(Job::getNoOfPosition)));
    	
    	Map<String, Integer> vancanciesMap = new HashMap<String, Integer>();
    	
    	 for (Map.Entry<String,Integer> entry : jobsMap.entrySet()){
    		 
    		 OpenVancancies ov = new OpenVancancies();
    		 ov.setName(entry.getKey());
    		 ov.setY(entry.getValue());
    		 ovsList.add(ov);
    	 }
    	 logger.debug("fetchALL from boardRepository{}");
    	 return ovsList;
    }
   
}




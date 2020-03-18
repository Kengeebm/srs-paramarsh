package com.paraamarsh.jobpost.web.rest;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import com.paraamarsh.jobpost.domain.OpenVancancies;
import com.paraamarsh.jobpost.security.SecurityUtils;
import com.paraamarsh.jobpost.service.DashBoardService;

/**
 * @author Anurag
 *
 */
@RestController
@RequestMapping("/api")
public class DashBoardResource {

	@Autowired
	DashBoardService boardService;
	private static final Logger Logger=LoggerFactory.getLogger(DashBoardResource.class);

	/**
	 * 
	 * @return
	 */
	@GetMapping("/jobs/open")
	public Long countByClosedFlagOpen() {
		Logger.debug("request for fetchALL boardService,ClosedFlagOpen"  );
		return boardService.countByClosedFlag();
	}
	
	@GetMapping("/jobs/close")
	public Long countByClosedFlagClose() {
		Logger.debug("request for fetchALL boardService,ClosedFlagClose"  );
		return boardService.countByClosedFlagClose();
	}

	/**
	 * @return
	 */
	@GetMapping("/jobs/count")
	public ResponseEntity<Long> recordCount(){
		Long record = boardService.recordCount();
		Logger.debug("request for fetchAll boardService:");
		return new ResponseEntity<Long>(record,HttpStatus.CREATED);
	}
	
	@GetMapping("/jobs/count/assignee")
	public ResponseEntity<Long> countByAssignee(){
		List<String> assignees = new ArrayList<String>();
		assignees.add(SecurityUtils.getCurrentUserLogin().toString());
		Long record = boardService.countByAssignee(assignees);
		Logger.debug("request for fetchAll boardService:",assignees );
		return new ResponseEntity<Long>(record,HttpStatus.CREATED);
	}
	
	@GetMapping("/jobs/vancancies/count")
	public ResponseEntity<List<OpenVancancies>> vancanciesCount(){
		List<OpenVancancies> openVan = boardService.openVacancies();
		Logger.debug("request for fetchAll boardService:");
		return new ResponseEntity<List<OpenVancancies>>(openVan,HttpStatus.CREATED);
	}
}

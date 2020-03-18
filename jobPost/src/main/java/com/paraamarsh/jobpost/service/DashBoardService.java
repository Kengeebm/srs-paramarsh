package com.paraamarsh.jobpost.service;

import java.util.List;
import java.util.Map;

import com.paraamarsh.jobpost.domain.OpenVancancies;

public interface DashBoardService {

	public Long countByClosedFlag();
	
	public Long countByClosedFlagClose();

	Long recordCount();

	Long countByAssignee(List<String> assignee);

	List<OpenVancancies> openVacancies();
}

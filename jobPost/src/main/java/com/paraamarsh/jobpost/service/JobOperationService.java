package com.paraamarsh.jobpost.service;

import java.util.List;

import com.paraamarsh.jobpost.domain.Candidate;

public interface JobOperationService {

	List<Candidate> getCandidatesFromIm(String query);
}

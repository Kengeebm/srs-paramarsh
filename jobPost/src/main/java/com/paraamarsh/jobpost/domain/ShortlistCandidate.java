package com.paraamarsh.jobpost.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShortlistCandidate {

	@Id
	Integer id;
	String status;
	String jobId;
	LocalDate date;
	Boolean scheduled;
	Boolean shortlisted;
	List<Candidate> candidatesList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Candidate> getCandidatesList() {
		
		return candidatesList;
	}

	public void setCandidatesList(List<Candidate> candidatesList) {
		this.candidatesList = candidatesList;
	}
}

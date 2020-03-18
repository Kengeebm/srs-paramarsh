package com.paraamarsh.jobpost.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Interview implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String level;

    private LocalDate interviewDate;

    private String interviewerName;

    private String interviewerEmail;

    private String interviewerPhone;

    private String status;

    private String comments;

    private Candidate candidate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public Interview level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public Interview interviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
        return this;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public Interview interviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
        return this;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public Interview interviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
        return this;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public String getInterviewerPhone() {
        return interviewerPhone;
    }

    public Interview interviewerPhone(String interviewerPhone) {
        this.interviewerPhone = interviewerPhone;
        return this;
    }

    public void setInterviewerPhone(String interviewerPhone) {
        this.interviewerPhone = interviewerPhone;
    }

    public String getStatus() {
        return status;
    }

    public Interview status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public Interview comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public Interview candidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Interview interview = (Interview) o;
        if (interview.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interview.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Interview [id=" + id + ", level=" + level + ", interviewDate=" + interviewDate + ", interviewerName="
				+ interviewerName + ", interviewerEmail=" + interviewerEmail + ", interviewerPhone=" + interviewerPhone
				+ ", status=" + status + ", comments=" + comments + ", candidate=" + candidate + "]";
	}

    
}
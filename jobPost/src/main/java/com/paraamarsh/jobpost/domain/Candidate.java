package com.paraamarsh.jobpost.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String name;

    private String email;

    private String phoneNo;
    
    private String gender;

    private String currentCompany;

    private Double experience;

    private String position;
    
    private String higestQualification;

    private String cit;

    private String state;

    private byte[] resume;

    private String resumeContentType;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updatedDate;

    private Set<Interview> interviews;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Candidate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Candidate email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Candidate phoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public Candidate gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public Candidate currentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
        return this;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public Double getExperience() {
        return experience;
    }

    public Candidate experience(Double experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public Candidate position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHigestQualification() {
        return higestQualification;
    }

    public Candidate higestQualification(String higestQualification) {
        this.higestQualification = higestQualification;
        return this;
    }

    public void setHigestQualification(String higestQualification) {
        this.higestQualification = higestQualification;
    }

    public String getCit() {
        return cit;
    }

    public Candidate cit(String cit) {
        this.cit = cit;
        return this;
    }

    public void setCit(String cit) {
        this.cit = cit;
    }

    public String getState() {
        return state;
    }

    public Candidate state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getResume() {
        return resume;
    }

    public Candidate resume(byte[] resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getResumeContentType() {
        return resumeContentType;
    }

    public Candidate resumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
        return this;
    }

    public void setResumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Candidate createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Candidate createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Candidate updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Candidate updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public Candidate interviews(Set<Interview> interviews) {
        this.interviews = interviews;
        return this;
    }

    public Candidate addInterview(Interview interview) {
        this.interviews.add(interview);
        interview.setCandidate(this);
        return this;
    }

    public Candidate removeInterview(Interview interview) {
        this.interviews.remove(interview);
        interview.setCandidate(null);
        return this;
    }

    public void setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
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
        Candidate candidate = (Candidate) o;
        if (candidate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Candidate{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNo='" + getPhoneNo() + "'" +
            ", gender='" + getGender() + "'" +
            ", currentCompany='" + getCurrentCompany() + "'" +
            ", experience=" + getExperience() +
            ", position='" + getPosition() + "'" +
            ", higestQualification='" + getHigestQualification() + "'" +
            ", cit='" + getCit() + "'" +
            ", state='" + getState() + "'" +
            ", resume='" + getResume() + "'" +
            ", resumeContentType='" + getResumeContentType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}

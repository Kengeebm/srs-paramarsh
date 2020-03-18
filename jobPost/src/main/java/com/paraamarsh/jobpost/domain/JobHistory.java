package com.paraamarsh.jobpost.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A JobHistory.
 */
@Document(collection = "job_history")
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
   
    @Field("job_id")
    private String jobId;

    @NotNull
    @Field("client_name")
    private String clientName;

    @Field("no_of_position")
    private Integer noOfPosition;

    @NotNull
    @Field("position_name")
    private String positionName;

    @NotNull
    @Field("location")
    private String location;

    @NotNull
    @Size(max = 1000)
    @Field("job_desc")
    private String jobDesc;

    @Field("from_exp")
    private Double fromExp;

    @Field("to_exp")
    private Double toExp;

    @Field("filled_position")
    private Integer filledPosition;
    
    @Field("closed_flag")
    private Boolean closedFlag=false;
    
    @Field("assignee")
    private List<String> assignee;

    @Field("comments")
    private String comments;

    @Field("start_date")
    private LocalDate startDate;

    @Field("end_date")
    private LocalDate endDate;

    @Field("closed_on")
    private LocalDateTime closedOn;

    @Field("opened_by")
    private String openedBy;

    @Field("closed_by")
    private String closedBy;

    @Field("updated_by")
    private String updatedBy;

    @Field("updated_date")
    private LocalDate updatedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public JobHistory jobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClientName() {
        return clientName;
    }

    public JobHistory clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getNoOfPosition() {
        return noOfPosition;
    }

    public JobHistory noOfPosition(Integer noOfPosition) {
        this.noOfPosition = noOfPosition;
        return this;
    }

    public void setNoOfPosition(Integer noOfPosition) {
        this.noOfPosition = noOfPosition;
    }

    public String getPositionName() {
        return positionName;
    }

    public JobHistory positionName(String positionName) {
        this.positionName = positionName;
        return this;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLocation() {
        return location;
    }

    public JobHistory location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public JobHistory jobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
        return this;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }


    /**
	 * @return the fromExp
	 */
	public Double getFromExp() {
		return fromExp;
	}

	/**
	 * @param fromExp the fromExp to set
	 */
	public void setFromExp(Double fromExp) {
		this.fromExp = fromExp;
	}

	public JobHistory fromExp(Double fromExp) {
		this.fromExp = fromExp;
		return this;
	}

	public JobHistory toExp(Double toExp) {
		this.toExp = toExp;
		return this;
	}
	/**
	 * @return the toExp
	 */
	public Double getToExp() {
		return toExp;
	}

	/**
	 * @param toExp the toExp to set
	 */
	public void setToExp(Double toExp) {
		this.toExp = toExp;
	}

	public Integer getFilledPosition() {
        return filledPosition;
    }

    public JobHistory filledPosition(Integer filledPosition) {
        this.filledPosition = filledPosition;
        return this;
    }

    public void setFilledPosition(Integer filledPosition) {
        this.filledPosition = filledPosition;
    }

    public String getComments() {
        return comments;
    }

    public JobHistory comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public JobHistory startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public JobHistory endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getClosedOn() {
        return closedOn;
    }

    public JobHistory closedOn(LocalDateTime closedOn) {
        this.closedOn = closedOn;
        return this;
    }

    public void setClosedOn(LocalDateTime closedOn) {
        this.closedOn = closedOn;
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public JobHistory openedBy(String openedBy) {
        this.openedBy = openedBy;
        return this;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public JobHistory closedBy(String closedBy) {
        this.closedBy = closedBy;
        return this;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public JobHistory updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public JobHistory updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
	public Boolean getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(Boolean closedFlag) {
		this.closedFlag = closedFlag;
	}
	
	public JobHistory closedFlag(Boolean closedFlag) {
		this.closedFlag = closedFlag;
		return this;
	}

	public List<String> getAssignee() {
		return assignee;
	}


	public void setAssignee(List<String> assignee) {
		this.assignee = assignee;
	}
	
	public JobHistory assignee(List<String> assignee) {
		this.assignee = assignee;
		return this;
	}
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobHistory)) {
            return false;
        }
        return id != null && id.equals(((JobHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "JobHistory [id=" + id + ", jobId=" + jobId + ", clientName=" + clientName + ", noOfPosition="
				+ noOfPosition + ", positionName=" + positionName + ", location=" + location + ", jobDesc=" + jobDesc
				+ ", fromExp=" + fromExp + ", toExp=" + toExp + ", filledPosition=" + filledPosition + ", comments="
				+ comments + ", startDate=" + startDate + ", endDate=" + endDate + ", closedOn=" + closedOn
				+ ", openedBy=" + openedBy + ", closedBy=" + closedBy + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}
}

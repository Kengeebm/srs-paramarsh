package com.paraamarsh.jobpost.domain;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.GetMapping;

import com.paraamarsh.jobpost.service.DashBoardService;

/**
 * A Job.
 */
@Document(collection = "job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id ;

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

    public Job jobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClientName() {
        return clientName;
    }

    public Job clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getNoOfPosition() {
        return noOfPosition;
    }

    public Job noOfPosition(Integer noOfPosition) {
        this.noOfPosition = noOfPosition;
        return this;
    }

    public void setNoOfPosition(Integer noOfPosition) {
        this.noOfPosition = noOfPosition;
    }

    public String getPositionName() {
        return positionName;
    }

    public Job positionName(String positionName) {
        this.positionName = positionName;
        return this;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLocation() {
        return location;
    }

    public Job location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public Job jobDesc(String jobDesc) {
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
	
	public Job FromExp(Double fromExp) {
		this.fromExp = fromExp;
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
	
	public Job ToExp(Double toExp) {
		this.toExp = toExp;
		return this;
	}


	public Integer getFilledPosition() {
        return filledPosition;
    }

    public Job filledPosition(Integer filledPosition) {
        this.filledPosition = filledPosition;
        return this;
    }

    public void setFilledPosition(Integer filledPosition) {
        this.filledPosition = filledPosition;
    }

    public String getComments() {
        return comments;
    }

    public Job comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Job startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Job endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getClosedOn() {
        return closedOn;
    }

    public Job closedOn(LocalDateTime closedOn) {
        this.closedOn = closedOn;
        return this;
    }

    public void setClosedOn(LocalDateTime localDateTime) {
        this.closedOn = localDateTime;
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public Job openedBy(String openedBy) {
        this.openedBy = openedBy;
        return this;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public Job closedBy(String closedBy) {
        this.closedBy = closedBy;
        return this;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Boolean getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(Boolean closedFlag) {
		this.closedFlag = closedFlag;
	}

	public List<String> getAssignee() {
		return assignee;
	}

	public void setAssignee(List<String> assignee) {
		this.assignee = assignee;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        return id != null && id.equals(((Job) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobId=" + jobId + ", clientName=" + clientName + ", noOfPosition=" + noOfPosition
				+ ", positionName=" + positionName + ", location=" + location + ", jobDesc=" + jobDesc + ", fromExp="
				+ fromExp + ", toExp=" + toExp + ", filledPosition=" + filledPosition + ", comments=" + comments
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", closedOn=" + closedOn + ", openedBy="
				+ openedBy + ", closedBy=" + closedBy + "]";
	}
	
	@Autowired
	DashBoardService boardService;
	
	@GetMapping("/openjobs")
	public Long countByClosedFlag() {
		return boardService.countByClosedFlag();
	}
}

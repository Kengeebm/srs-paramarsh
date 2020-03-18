package com.paraamarsh.jobpost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "job_no")
public class JobNo implements Serializable {

    @Id
    private String id;

    private Integer name;

    public JobNo() {
        super();
    }


    public JobNo(String id, Integer name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobNo{" +
            "id='" + id + '\'' +
            ", name=" + name +
            '}';
    }
}

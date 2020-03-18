package com.paraamarsh.jobpost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "job_experience")
public class JobExperience implements Serializable {

    private static final long serialVersionUID = -4760427861915014363L;

    @Id
    private String id;

    private String name;

    public JobExperience() {
        super();
    }

    public JobExperience(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobExperience [id=" + id + ", name=" + name + "]";
    }

}

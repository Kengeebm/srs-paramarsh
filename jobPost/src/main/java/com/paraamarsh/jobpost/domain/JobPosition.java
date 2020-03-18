package com.paraamarsh.jobpost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "job_position")
public class JobPosition implements Serializable {


    @Id
    private String id;

    private String name;

    public JobPosition() {
        super();
    }

    public JobPosition(String id, String name) {
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
        return "ClientName{" +
            "id=" + id +
            ", name=" + name +
            '}';
    }
}

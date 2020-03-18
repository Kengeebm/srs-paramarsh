package com.paraamarsh.jobpost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user_name")

public class UserName implements Serializable {


    @Id
    private String id;

    private String name;

    public UserName() {
        super();
    }

    public UserName(String id, String name) {
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

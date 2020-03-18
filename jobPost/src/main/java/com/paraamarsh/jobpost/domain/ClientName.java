package com.paraamarsh.jobpost.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "client_name")
public class ClientName implements Serializable {

    private static final long serialVersionUID = ClientName.class.getClass().hashCode();

    @Id
    private String id;

    private String name;

    public ClientName() {
        super();
    }

    public ClientName(String id, String name) {
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

    @java.lang.Override
    public java.lang.String toString() {
        return "ClientName{" +
            "id=" + id +
            ", name=" + name +
            '}';
    }
}

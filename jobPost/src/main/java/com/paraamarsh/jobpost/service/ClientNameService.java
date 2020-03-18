package com.paraamarsh.jobpost.service;

import com.paraamarsh.jobpost.domain.ClientName;

import java.util.List;

public interface ClientNameService {

    List<ClientName> findAll();

    ClientName findById(String id);

    void save(ClientName entity);

    void delete(String id);
}

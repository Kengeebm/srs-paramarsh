package com.paraamarsh.jobpost.service;

import com.paraamarsh.jobpost.domain.UserName;

import java.util.List;

public interface UserNameService {

    List<UserName> findAll();

    UserName findById(String id);

    void save(UserName entity);

    void delete(String id);
}

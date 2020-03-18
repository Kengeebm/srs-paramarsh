package com.paraamarsh.jobpost.service;

import com.paraamarsh.jobpost.domain.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location findById(String id);

    void save(Location entity);

    void delete(String id);


}

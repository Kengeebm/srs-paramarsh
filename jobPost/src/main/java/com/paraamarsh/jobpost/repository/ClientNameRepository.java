package com.paraamarsh.jobpost.repository;

import com.paraamarsh.jobpost.domain.ClientName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientNameRepository extends MongoRepository<ClientName, String> {

}

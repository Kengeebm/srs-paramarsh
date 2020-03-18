package com.paraamarsh.jobpost.repository;

import com.paraamarsh.jobpost.domain.UserName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNameRepository extends MongoRepository<UserName, String> {

}

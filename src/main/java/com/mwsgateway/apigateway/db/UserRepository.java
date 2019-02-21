package com.mwsgateway.apigateway.db;

import com.mwsgateway.apigateway.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends MongoRepository<User, UUID> {



}

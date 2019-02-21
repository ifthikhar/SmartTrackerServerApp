package com.apigateway.db;

import com.apigateway.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository  extends MongoRepository<User, UUID> {



}

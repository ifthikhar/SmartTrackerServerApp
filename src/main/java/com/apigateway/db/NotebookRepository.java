package com.apigateway.db;

import com.apigateway.model.Notebook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface NotebookRepository extends MongoRepository<Notebook, UUID> {
}



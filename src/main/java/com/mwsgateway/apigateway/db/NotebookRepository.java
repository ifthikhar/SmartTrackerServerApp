package com.mwsgateway.apigateway.db;

import com.mwsgateway.apigateway.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface NotebookRepository extends MongoRepository<Notebook, UUID> {
}



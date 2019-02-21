package com.apigateway.db;

import com.apigateway.model.Notebook;
import com.apigateway.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends MongoRepository<Note, UUID> {
    List<Note> findAllByNotebook(Notebook notebook);
}

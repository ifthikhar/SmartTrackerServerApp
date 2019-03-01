package com.apigateway.db;

import com.apigateway.model.User;
import com.apigateway.model.Note;
import com.apigateway.model.Notebook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * This component will only execute (and get instantiated) if the
 * property noteit.db.recreate is set to true in the
 * application.properties file
 */

@Component
//@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {

    private NotebookRepository notebookRepository;

    private NoteRepository noteRepository;

    private UserRepository userRepository;

    public DbSeeder(NotebookRepository notebookRepository,
                    NoteRepository noteRepository, UserRepository userRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
        this.userRepository=userRepository;
    }


    @Override
    public void run(String... args) {
        // Remove all existing entities
        this.notebookRepository.deleteAll();
        this.noteRepository.deleteAll();


        // Save a default notebook
        Notebook defaultNotebook = new Notebook("Default");
        this.notebookRepository.save(defaultNotebook);

        Notebook quotesNotebook = new Notebook("Quotes");
        this.notebookRepository.save(quotesNotebook);

        // Save the welcome note
        Note note = new Note("Hello", "Welcome to Note It", defaultNotebook);
        this.noteRepository.save(note);

        // Save a quote note
        Note quoteNote = new Note("Latin Quote", "Carpe Diem", quotesNotebook);
        this.noteRepository.save(quoteNote);

        // save the default user information

        User user = new User("ifthikhar","nasardeen","@gmail.com","111");
        this.userRepository.save(user);


        System.out.println("Initialized database");
    }
}

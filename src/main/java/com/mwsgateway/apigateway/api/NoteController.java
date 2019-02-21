package com.mwsgateway.apigateway.api;

import com.mwsgateway.apigateway.Mapper;
import com.mwsgateway.apigateway.api.viewmodel.NoteViewModel;
import com.mwsgateway.apigateway.db.NoteRepository;
import com.mwsgateway.apigateway.db.NotebookRepository;
import com.mwsgateway.apigateway.model.Note;
import com.mwsgateway.apigateway.model.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
Requests can be tested using the built in HTTP Rest Client. Use the
examples found in 'noteit.http' file.
 */

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {

    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;

    private Mapper mapper;

    public NoteController(NoteRepository noteRepository, NotebookRepository notebookRepository, Mapper mapper) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteViewModel> all() {
        List<Note> notes = this.noteRepository.findAll();

        // map from entity to view model
        List<NoteViewModel> list = new ArrayList<>();
        for (Note note : notes) {
            NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);
            list.add(noteViewModel);
        }


        return list;
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel byId(@PathVariable String id) {
        Note note = this.noteRepository.findById(UUID.fromString(id)).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);

        return noteViewModel;
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        Optional<Notebook> notebook = this.notebookRepository.findById(UUID.fromString(notebookId));
        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }





        // map to note view model
       // Optional<NoteViewModel> notesViewModel = notes.stream()
           //     .map(note -> this.mapper.convertToNoteViewModel(note).collect(Collectors.toList());


        List<NoteViewModel> notelist = new ArrayList<>();
        for (Note note : notes) {
            NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);
            notelist.add(noteViewModel);
        }



        return notelist;
    }

    @PostMapping
    public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Note noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);

        // save note instance to db
        this.noteRepository.save(noteEntity);

        return noteEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteRepository.deleteById(UUID.fromString(id));
    }
}

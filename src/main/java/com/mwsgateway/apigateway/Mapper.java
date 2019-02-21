package com.mwsgateway.apigateway;

import com.mwsgateway.apigateway.api.viewmodel.NoteViewModel;
import com.mwsgateway.apigateway.api.viewmodel.NotebookViewModel;
import com.mwsgateway.apigateway.api.viewmodel.UserViewModel;
import com.mwsgateway.apigateway.db.NotebookRepository;
import com.mwsgateway.apigateway.model.Note;
import com.mwsgateway.apigateway.model.Notebook;
import com.mwsgateway.apigateway.model.User;
import org.springframework.stereotype.Component;


import java.util.UUID;

/**
 * Component that handles all mappings in this project
 * - entity to view model
 * - view model to entity
 * <p>
 * All mappings are handled here, but in production code this is not the
 * best approach. You can take a look at ModelMapper project or at least split mapping classes
 * across many files.
 */

@Component
public class Mapper {
    private NotebookRepository notebookRepository;

    public Mapper(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public NoteViewModel convertToNoteViewModel(Note entity) {
        NoteViewModel viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId().toString());
        viewModel.setLastModifiedOn(entity.getLastModifiedOn());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId().toString());

        return viewModel;
    }

    public Note convertToNoteEntity(NoteViewModel viewModel) {
        Notebook notebook = this.notebookRepository.findById(UUID.fromString(viewModel.getNotebookId())).get();
        Note entity = new Note(viewModel.getId(), viewModel.getTitle(), viewModel.getText(), notebook);

        return entity;
    }

    public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
        NotebookViewModel viewModel = new NotebookViewModel();
        viewModel.setId(entity.getId().toString());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());

        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
        Notebook entity = new Notebook(viewModel.getId(), viewModel.getName());

        return entity;
    }

    public User convertToUserEntity(UserViewModel userViewModel) {
        User userEntity = new User(userViewModel.getName(),userViewModel.getEmail(),userViewModel.getPassword());

        return userEntity;
    }

}

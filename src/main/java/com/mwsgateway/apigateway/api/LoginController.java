package com.mwsgateway.apigateway.api;

import com.mwsgateway.apigateway.Mapper;
import com.mwsgateway.apigateway.api.viewmodel.UserViewModel;
import com.mwsgateway.apigateway.db.UserRepository;
import com.mwsgateway.apigateway.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    private UserRepository userRepository;

    private Mapper mapper;

    public LoginController(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @PostMapping
    public User save(@RequestBody UserViewModel userViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        User usersEntity = this.mapper.convertToUserEntity(userViewModel);

        // save notebookEntity instance to db
        this.userRepository.save(usersEntity);

        return usersEntity;
    }


    @GetMapping("/saveUsers")
    public List<User> all() {

        return (List<User>) this.userRepository.findAll();
    }








}


package com.apigateway.api;

import com.apigateway.Mapper;
import com.apigateway.api.viewmodel.UserViewModel;
import com.apigateway.db.UserRepository;
import com.apigateway.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/register")
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


    @GetMapping("/allUsers")
    public List<User> all() {

        return (List<User>) this.userRepository.findAll();
    }

    @GetMapping("/checkLogin")
    public List<User> check() {

        return (List<User>) this.userRepository.findAll();
    }









}


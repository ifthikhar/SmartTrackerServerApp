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

    @PostMapping("/checkLogin")
    public Boolean check(@RequestBody UserViewModel userViewModel,
                            BindingResult bindingResult) {
        System.out.println("In to the method for checking the login");
        Boolean isLogin = null;
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        User usersEntity = this.mapper.convertToUserEntity(userViewModel);

        List<User> users= (List<User>) this.userRepository.findAll();
        System.out.println("total number of users in DB"+ users.size());
        for(User u:users)
        {
           System.out.println("DB ---> the email address is " + u.getEmail() + "password is" + u.getPassword());
            System.out.println("REQUEST  ---> the email address is " + usersEntity.getEmail() + "password is" + usersEntity.getPassword());
            if(u.getEmail().equals(usersEntity.getEmail())&& u.getPassword().equals(usersEntity.getPassword()))
            {
                isLogin=true;

            }

            else
            {
                isLogin=false;

            }

        }

        return isLogin;
    }









}


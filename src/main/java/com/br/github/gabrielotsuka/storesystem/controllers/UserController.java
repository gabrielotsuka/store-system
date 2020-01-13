package com.br.github.gabrielotsuka.storesystem.controllers;

import com.br.github.gabrielotsuka.storesystem.controllers.request.UserRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.UserResponse;
import com.br.github.gabrielotsuka.storesystem.models.User;
import com.br.github.gabrielotsuka.storesystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest dto){
        User user1 = userService.save(dto.toUser());
        return new ResponseEntity<>(UserResponse.toResponse(user1), HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }
}

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
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest dto) {
        return userService.save(dto.toUser());
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody UserRequest newUser){
        return userService.editUser(id, newUser.toUser());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        return userService.deleteUser(id);
    }
}

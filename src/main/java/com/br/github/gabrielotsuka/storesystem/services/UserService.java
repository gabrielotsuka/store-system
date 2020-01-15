package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.response.UserResponse;
import com.br.github.gabrielotsuka.storesystem.error.ResourceNotFoundException;
import com.br.github.gabrielotsuka.storesystem.models.User;
import com.br.github.gabrielotsuka.storesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserResponse> save(User user){
        userRepository.save(user);
        return new ResponseEntity<UserResponse>(UserResponse.toResponse(user), HttpStatus.CREATED);
    }

    public List<UserResponse> getUsers(){
        List<User> arr = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        arr.forEach(temp -> {
            response.add(UserResponse.toResponse(temp));
        });
        return response;
    }

    public ResponseEntity<UserResponse> getUserById(Long id){
        User user = verifyUserExistence(id);
        return new ResponseEntity<UserResponse>(UserResponse.toResponse(user), HttpStatus.OK);
    }

    public ResponseEntity<UserResponse> editUser(Long id, User newUser){
        User user = verifyUserExistence(id);
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPwd(newUser.getPwd());
        userRepository.save(user);
        return new ResponseEntity<UserResponse>(UserResponse.toResponse(user), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUser(Long id){
        User user = verifyUserExistence(id);
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User verifyUserExistence(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new ResourceNotFoundException("User not found. ID: "+id);
        else
            return user.get();
    }
}

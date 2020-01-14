package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.request.UserRequest;
import com.br.github.gabrielotsuka.storesystem.controllers.response.UserResponse;
import com.br.github.gabrielotsuka.storesystem.models.User;
import com.br.github.gabrielotsuka.storesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import javax.xml.ws.Response;
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
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(UserResponse.toResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserResponse> editUser(Long id, User newUser){
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()){
            User user = oldUser.get();
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPwd(newUser.getPwd());
            userRepository.save(user);
            return new ResponseEntity<UserResponse>(UserResponse.toResponse(user), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

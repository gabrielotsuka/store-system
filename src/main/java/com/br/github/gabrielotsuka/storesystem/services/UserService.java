package com.br.github.gabrielotsuka.storesystem.services;

import com.br.github.gabrielotsuka.storesystem.controllers.response.UserResponse;
import com.br.github.gabrielotsuka.storesystem.models.User;
import com.br.github.gabrielotsuka.storesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<UserResponse> getUsers(){
        List<User> arr = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        arr.forEach(temp -> {
            response.add(UserResponse.toResponse(temp));
        });

        return response;
    }
}

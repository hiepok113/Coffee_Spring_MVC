package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
     public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteUserById(Long id) {
    userRepository.deleteById(id);
    }
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entities.User;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    Optional<User> findUserById(long id);
    User findCustomerByUsername(String username);
    boolean loginAuthenticator(String username, String password);
    void getAllusers();
    String cryptPassword(String password);
    
    
    
}

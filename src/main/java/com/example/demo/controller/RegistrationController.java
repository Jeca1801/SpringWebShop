/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    
    @Autowired
    UserServiceImpl userServiceImpl;
    
    @GetMapping("/registration")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String submitRegistrationForm(@ModelAttribute User user){
        user.setPassword(userServiceImpl.cryptPassword(user.getPassword())); 
        userServiceImpl.createUser(user);
        return "registrationcomplete";
    }    
}

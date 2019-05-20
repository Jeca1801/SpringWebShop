
package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.form.LoginForm;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
      @Autowired
    UserServiceImpl userServiceImpl;
      
      User user = null;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getLoginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute LoginForm loginForm, Model model){
        //hämtar loginforms username och password
        String username = loginForm.getUsername();
        String password = userServiceImpl.cryptPassword(loginForm.getPassword());
        
        
        // kollar om användarnamnet och passwordet matchar och isf tar dig till kundsidan
        if(userServiceImpl.loginAuthenticator(username, password)){ 
            user = userServiceImpl.findCustomerByUsername(username);
               return "redirect:/customer/";
        }
        else{
        //om det är fel username eller password
        model.addAttribute("invalidCredentials", true);
        //skickar dom tillbaka till samma sida
        return "login";
        }

    }
    
}

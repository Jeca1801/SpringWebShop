package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void getAllusers() {
        userRepository.findAll();
    }

    @Override
    public boolean loginAuthenticator(String username, String password) {
        List<User> users = userRepository.findAll();
        for(User u : users){
            if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User findCustomerByUsername(String username) {
       List<User> users = userRepository.findAll();
       for(User u : users){
           if(u.getUsername().equalsIgnoreCase(username)){
               return u;
           }
       }
       return null;
    }

    @Override
    public String cryptPassword(String password) {
        String algorithm = "SHA";

        byte[] plainText = password.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

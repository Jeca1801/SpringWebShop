
package com.example.demo.repository;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner{
    private UserRepository userRepository;
    private ProductRepository productRepository;
    
    public DbInit(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository; 
        this.productRepository = productRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        
        User kenny = new User("kenny", "kenny", "kenny123","customer");
        User johnny = new User("johnny", "johnny", "johnny123","admin");
        User vincent = new User("vincent", "vincent", "vincent123","premium");
        
        List<User> users = Arrays.asList(kenny, johnny, vincent);
        
        //sparar alla users i db när programmt körs
        this.userRepository.saveAll(users);
       
        Product shoe1 = new Product("shoe1", "nike", "airforce", "nice shoe", 100);
        Product shoe2 = new Product("shoe2", "nike", "airforce", "nice shoe", 100);
        Product shoe3 = new Product("shoe3", "nike", "airforce", "nice shoe", 100);
        
        List<Product> shoes = Arrays.asList(shoe1, shoe2, shoe3);
        
        //sparar alla shor när programmt körs
        this.productRepository.saveAll(shoes);
        
    }   
}



package com.example.demo.service;

import com.example.demo.entities.Cart;
import com.example.demo.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart saveOrUpdate(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getCartByUsername(String username) {
        List<Cart> carts = cartRepository.findAll();
        for(Cart c : carts){
            if(c.getUser().getUsername() == username){
                return c;
            }
        }
        return null;
    }
/*
    @Override
    public double sumPriceInCart(String username) {
        double total = 0;
        List<Cart> cartList = cartRepository.findAll();
        if(!cartList.isEmpty()){
            for(Cart c : cartList){
                if(c.getUser().getUsername().equalsIgnoreCase(username)){
                    total = c.getId();
                }
            }
        }
        return total;
    }    
*/
}

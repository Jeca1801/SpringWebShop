
package com.example.demo.service;

import com.example.demo.entities.Cart;

public interface CartService {
    Cart saveOrUpdate(Cart cart);
    Cart getCartByUsername(String username);
   // double sumPriceInCart(String username);
}

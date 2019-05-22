/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entities.CartProduct;

public interface CartProductService {
    CartProduct saveOrUpdateCartProduct(CartProduct cp);
    
}

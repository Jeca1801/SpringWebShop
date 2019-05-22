/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entities.CartProduct;
import com.example.demo.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartProductServiceImpl implements CartProductService{
    
    @Autowired
    CartProductRepository cartProductRepository;

    @Override
    public CartProduct saveOrUpdateCartProduct(CartProduct cp) {
        cartProductRepository.save(cp);
        return cp;
    }  
}

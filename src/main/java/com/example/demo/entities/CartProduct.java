
package com.example.demo.entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class CartProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    //@ManyToOne(cascade = {CascadeType.MERGE})
    //@JoinColumn
    @OneToOne
    private Product product;
    
    @OneToOne
    private Cart cart;
    
    
    
    
    
    //flera cartproduct tillhör endast en product
    
   // @ManyToOne(cascade = {CascadeType.MERGE})
   // @JoinColumn
    
    
    private int quantity;
    
    public CartProduct(){
        
    }
    public CartProduct(Product product, Cart cart, int quantity){
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}

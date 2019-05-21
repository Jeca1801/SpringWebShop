
package com.example.demo.controller;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartProduct;
import com.example.demo.entities.Product;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.service.CartServiceImpl;
import com.example.demo.service.ProductServiceImpl;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    @Autowired
    private CartServiceImpl cartServiceImpl;
    
    @Autowired
    private CartProductRepository cartProductRepository;
    
    @Inject
    LoginController lg;
    
    private int quantity;

    @RequestMapping(value="/customer/", method = RequestMethod.GET)
    public String registrationForm(ModelMap modelmap, Model model){
        model.addAttribute("username", lg.user.getUsername());
        System.out.println(lg.user.getUsername());
        modelmap.put("products", productServiceImpl.getAllProducts());
        return "customer";
    }
    
    @RequestMapping(value="/buy/{productId}/", method = RequestMethod.GET)
    public String addToCart(@PathVariable(value = "productId") long productId){
        
        Product currentProduct = productServiceImpl.findProductByProductId(productId);
        Cart currentCart = cartServiceImpl.getCartByUsername(lg.user.getUsername());
            
        //Ifall cart är null, skapa ny cart
        if (currentCart == null)
        {
            currentCart = new Cart();
            
            //Sätt user till denna cart
            currentCart.setUser(lg.user);
            
            //Sätt cart till user
            lg.user.setCart(currentCart); 
            
            //Spara carten i databasen, så Id skapas
            cartServiceImpl.saveOrUpdate(currentCart);
            
            //hämta carten igen, för när carten har sparats i databasen, då får carten sin Id
            currentCart = cartServiceImpl.getCartByUsername(lg.user.getUsername());
        }

        //Skapa ny cartProduct pekad till cartens Id
        cartProductRepository.save(new CartProduct(currentProduct, currentCart, 1));

        return "redirect:/customer/";
    } 

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }    
}

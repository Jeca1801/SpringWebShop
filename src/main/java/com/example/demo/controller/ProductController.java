
package com.example.demo.controller;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.service.CartServiceImpl;
import com.example.demo.service.ProductServiceImpl;
import com.example.demo.service.UserServiceImpl;
import java.util.Collections;
import java.util.List;
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
    
    private User user = null;
    
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    @Autowired
    private CartServiceImpl cartServiceImpl;
    
    @Inject
    LoginController lg;

    

    
    @RequestMapping(value="/customer/", method = RequestMethod.GET)
    public String registrationForm(ModelMap modelmap, Model model){
        model.addAttribute("username", lg.user.getUsername());
        System.out.println(lg.user.getUsername());
        modelmap.put("products", productServiceImpl.getAllProducts());
        return "customer";
    }
    
    @RequestMapping(value="/buy/{productId}/", method = RequestMethod.GET)
    public String addToCart(@PathVariable(value = "productId") long productId){
        //1.Hämtar allt för att adda i varukorgen
        //hittar den nuvarande usern, produkten och vilken cart det är baserat på username.
        Product currentProduct = productServiceImpl.findProductByProductId(productId);
        Cart currentCart = cartServiceImpl.getCartByUsername(lg.user.getUsername());
        

        //kollar om korgen är tom. skapa upp en ny cart och adda current product och spara i databasen
        if(currentCart != null){ 
            //2.Stoppar in allt i varukorgen
            //hämtar produktlistan som är länkad till usernamet
            List<Product> products = currentCart.getProductList();
            //addar produkten som vi fått baserat på produkt idn
            products.add(currentProduct);
            //set'ar våran carts produktlista till den nuvarande produkten
            currentCart.setProductList(products);
            //här kan vi seta priset
            currentCart.setPrice(currentProduct.getPrice());
            //sparar produkten i databasen
            cartServiceImpl.saveOrUpdate(currentCart);
            return "redirect:/customer/";
        }
        
        Cart newCart = new Cart();
        newCart.setProductList(Collections.singletonList(currentProduct));
        newCart.setUser(lg.user);
        newCart = cartServiceImpl.saveOrUpdate(newCart);
        //savear den innan så current user får en cart med ett id
        lg.user.setCart(newCart);
        //kan spara men även uppdatera en ny customer(denna uppdaterar så att carten och usern länkas ihop)
        userServiceImpl.createUser(lg.user);
        
        return "redirect:/customer/";
    } 

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}

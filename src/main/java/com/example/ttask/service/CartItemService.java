package com.example.ttask.service;


import com.example.ttask.model.CartItem;
import com.example.ttask.repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ttask.repository.CartItemRepo;
import com.example.ttask.repository.CartRepo;
import com.example.ttask.model.Cart;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class CartItemService {
    
    @Autowired
    private CartItemRepo cartItemRepo;
    
    @Autowired
    private CartRepo cartRepo;
    
    public List<CartItem> getAll() {
        return cartItemRepo.findAll();
    }
    
    public Long removeCartItem(String id) {
        
        System.out.println("Delete cart item by id: " + id);
        
        try{
            CartItem cartItem = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart Item not found"));
            String cart_id = cartItem.get_cart_id();
            Cart cart = cartRepo.findById(cart_id).orElseThrow(() -> new RuntimeException("Cart not found"));
            
            for (Iterator<CartItem> iter = cart.get_cart_items().listIterator(); iter.hasNext(); ) {
                    CartItem item = iter.next();
                    if (item.get_id().equals(id)) {
                        iter.remove();
                    }
            }  
            
            cartRepo.save(cart);
            cartItemRepo.deleteById(id);
            return 1L;
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return -1L;
        } 
    }
    
    
    
    public Cart addCartItem(CartItem cartItem){
        
        System.out.println("Add cart item to cart id: " + cartItem.get_cart_id());
        
        try{
            String cart_id = cartItem.get_cart_id();
            Cart cart = cartRepo.findById(cart_id).orElseThrow(() -> new RuntimeException("Cart not found"));
            cartItem.set_timestamp(LocalDateTime.now());
            CartItem newItem = cartItemRepo.save(cartItem);
            cartItem.set_id(newItem.get_id());        
            cart.get_cart_items().add(cartItem);
    
            return cartRepo.save(cart);
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
       
}

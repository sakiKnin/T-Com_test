package com.example.ttask.service;

import com.example.ttask.model.Cart;
import com.example.ttask.model.CartItem;
import com.example.ttask.repository.CartRepo;
import com.example.ttask.repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDateTime;
 
@Service
public class CartService {
    
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    
    public List<Cart> getAll() {
        try{
            return cartRepo.findAll();
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
     
    public Cart createCart(Cart cart) {
        
        System.out.println("Create new cart with items");
        
        try{
            
            UUID uuid = UUID.randomUUID();
            for (CartItem item : cart.get_cart_items()) {
                item.set_timestamp(LocalDateTime.now());
                item.set_cart_id(uuid.toString());
                CartItem newItem = cartItemRepo.save(item);
                item.set_id(newItem.get_id());
            }
            cart.set_id(uuid.toString());
            return cartRepo.save(cart);
        
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
    
    public Long evictCart(String id) {
        
        System.out.println("Delete cart by id: " + id);
        
        try{
            Cart cart = cartRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
            System.out.println(cart.get_cart_items());
            for(CartItem item : cart.get_cart_items()){
                cartItemRepo.deleteById(item.get_id());
            }
            cartRepo.deleteById(id);
            return 1L;
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return -1L;
        }
        
    }
    

    
}

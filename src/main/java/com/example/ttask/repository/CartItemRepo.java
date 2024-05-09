package com.example.ttask.repository;

import com.example.ttask.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface CartItemRepo 
        extends MongoRepository<CartItem, String> {
    
}
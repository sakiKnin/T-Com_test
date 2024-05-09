package com.example.ttask.repository;

import com.example.ttask.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepo 
        extends MongoRepository<Cart, String> {
    
}
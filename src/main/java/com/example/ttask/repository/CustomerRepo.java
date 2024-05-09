package com.example.ttask.repository;
 
import com.example.ttask.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface CustomerRepo 
        extends MongoRepository<Customer, String> {
    
            
    
}

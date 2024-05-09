package com.example.ttask.service;

import com.example.ttask.model.Customer;
import com.example.ttask.model.Cart;
import com.example.ttask.model.CartItem;
import com.example.ttask.repository.CustomerRepo;
import com.example.ttask.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private CartRepo cartRepo;
    
    private final MongoTemplate mongoTemplate;

    public CustomerService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepo.insert(customer);
    }
    
    public List<Cart> getCartByCustomerId(String id) {
        
        System.out.println("Get carts by customer id: " + id);
        
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("_customer_id").is(id));
            List<Cart> carts = mongoTemplate.find(query, Cart.class);
                   
            return carts;
        
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
    
    public Map<String,Map<String,Integer>> itemStats(String startDate, String endDate) {
        
        Map<String,Map<String,Integer>> map = new HashMap<>();
        try{
            Query query = new Query();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dateTime1 = LocalDateTime.parse(startDate, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(endDate, formatter);
           
            query.addCriteria(Criteria.where("_action")
                    .in("ADD","MODIFY","DELETE")
                    .and("_timestamp").gte(dateTime1).lte(dateTime2));
            List<CartItem> cartItems = mongoTemplate.find(query, CartItem.class);
             
            for(CartItem item : cartItems){
                if(map.get(item.get_product_id())==null){
                     Map<String, Integer> map2 = new HashMap<>();
                     map2.put(item.get_action(),map2.getOrDefault(item.get_action(),0)+1);
                     map.put(item.get_product_id(),map2);
                }else{
                    Map<String,Integer> map2 = map.get(item.get_product_id());
                    map2.put(item.get_action(),map2.getOrDefault(item.get_action(),0)+1);
                    map.put(item.get_product_id(),map2);
                }    
            }
               
            return map;
        
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
       
}

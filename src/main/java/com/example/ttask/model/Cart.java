package com.example.ttask.model;

import com.example.ttask.model.CartItem;
import jakarta.persistence.*;
import lombok.Data; 
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
 
@Data
@Document(collection = "cart")
public class Cart {
    
    @Valid
    
    @Id
    private String _id;
    @NotNull(message = "_customer_id is mandatory")
    private String _customer_id;
    @NotNull(message = "_cart_items is mandatory")
    private List<@Valid CartItem> _cart_items;
    
    public List<CartItem> get_cart_items(){
        return this._cart_items;
    }
}








 

 

package com.example.ttask.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank; 

@Data
@Document(collection = "cart_item")
public class CartItem {
    @Valid
    
    @Id
    private String _id;
    private String _cart_id;
    @NotNull(message = "_product_id is mandatory")
    @NotBlank(message = "_product_id can't be empty")
    private String _product_id;
    @NotNull(message = "_price_id is mandatory")
    @NotBlank(message = "_price_id can't be empty")
    private String _price_id;
    @NotNull(message = "_action is mandatory")
    @NotBlank(message = "_action can't be empty")
    private String _action;
    private LocalDateTime _timestamp;
    
    public String get_id(){
            return this._id;
    }
    
    public void set_id(String id){
            this._id=id;
    }
    
    public String get_cart_id(){
            return this._cart_id;
    }
    
    public void set_cart_id(String cart_id){
            this._cart_id=cart_id;
    }
    
    public String get_product_id(){
            return this._product_id;
    }
    
    public String get_price_id(){
            return this._price_id;
    }
    
    public String get_action(){
            return this._action;
    }
    
    public LocalDateTime get_timestamp(){
            return this._timestamp;
    }
    
    public void set_timestamp(LocalDateTime timestamp){
            this._timestamp=timestamp;
    }
}

package com.example.ttask.controller;

import com.example.ttask.model.Cart;
import com.example.ttask.model.CartItem;
import com.example.ttask.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public ResponseEntity<List<Cart>> getAll() {
        
        List<Cart> cartList =  cartService.getAll();
        if(cartList!=null){
            return new ResponseEntity<>(cartList, HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(cartList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(produces="application/json", method= RequestMethod.POST)
    public ResponseEntity<?> cartCreate(@RequestHeader(value = "apikey", required=false) String apiKey, @Valid @RequestBody Cart cart){
        Cart createdCustomer =  cartService.createCart(cart);
        if(createdCustomer!=null){
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED); 
        }else{
            return new ResponseEntity<>(createdCustomer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @RequestMapping(produces="application/json", method= RequestMethod.DELETE)
    public ResponseEntity<Long> evictCart(@RequestParam(required = true) String id){
        long status = cartService.evictCart(id);
        if(status==1){
            return new ResponseEntity<>(status, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exep){
        Map<String,String> errors = new HashMap<>();
        
        exep.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName,errorMessage);
        });
                
      return errors;
    }
}
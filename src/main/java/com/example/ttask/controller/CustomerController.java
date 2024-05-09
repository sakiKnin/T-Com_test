package com.example.ttask.controller;

import com.example.ttask.model.*;
import com.example.ttask.service.CustomerService;
import com.example.ttask.service.CartService;
import com.example.ttask.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
     
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> listCustomer =  customerService.getAll();
        if(listCustomer!=null){
            return new ResponseEntity<>(listCustomer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(listCustomer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value="/cart-content", produces="application/json", method= RequestMethod.GET)
    public ResponseEntity<List<Cart>> getCartByCustomerId(@RequestParam(required = true) String id){
        List<Cart> cartList =  customerService.getCartByCustomerId(id);
        if(cartList!=null){
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(cartList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @RequestMapping(produces="application/json", method= RequestMethod.POST)
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer){
        Customer retCustomer =  customerService.createCustomer(customer);
        if(retCustomer!=null){
            return new ResponseEntity<>(retCustomer, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(retCustomer, HttpStatus.INTERNAL_SERVER_ERROR);
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

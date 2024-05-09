package com.example.ttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.ttask.model.CartItem;
import com.example.ttask.model.Cart;
import com.example.ttask.service.CartItemService;
import com.example.ttask.service.CartService;
import com.example.ttask.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {
    
    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public ResponseEntity<List<CartItem>> getAll() {
        List<CartItem> cartItems = cartItemService.getAll();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    
    @RequestMapping(produces="application/json", method= RequestMethod.POST)
    public ResponseEntity<Cart> addCartItem(@RequestBody CartItem cartItem){
        
        Cart cart =  cartItemService.addCartItem(cartItem);
        
        if(cart!=null){
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(cart, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(produces="application/json", method= RequestMethod.DELETE)
    public ResponseEntity<Long> removeCartItem(@RequestParam(required = true) String id){
        long status = cartItemService.removeCartItem(id);
        if(status==1){
            return new ResponseEntity<>(status, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value="stats", produces="application/json", method= RequestMethod.GET)
    public ResponseEntity<Map<String,Map<String,Integer>>> itemStats(@RequestParam(required = true) String start_date,@RequestParam(required = true) String end_date){
        Map<String,Map<String,Integer>> stats =  customerService.itemStats(start_date,end_date);
        if(stats!=null){
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(stats, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
}

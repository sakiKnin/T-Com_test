package com.example.ttask.model;

import jakarta.persistence.*;
import lombok.Data; 
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;


@Data
@Document(collection = "price")
public class Price {
    
    @Valid
    
    @Id
    private String _id;
    
    @Indexed(unique=true)
    @NotNull(message = "_product_id is mandatory")
    @NotBlank(message = "_product_id can't be blank")
    private String productId;
    @NotNull(message = "_type is mandatory")
    @NotBlank(message = "_type can't be blank")
    private int _type;
    private int _rec_period;
    private String _unit;
    @NotNull(message = "_amount is mandatory")
    @NotBlank(message = "_amount can't be blank")
    private int _amount;
}

package com.example.ttask.model;

import jakarta.persistence.*;
import lombok.Data; 
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Document(collection = "product")
public class Product {   
    
    @Valid
    
    @Id
    private String _id;
    
    @Indexed(unique=true)
    @NotNull(message = "_name is mandatory")
    @NotBlank(message = "_name can't be blank")
    private String _name;
}


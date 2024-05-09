package com.example.ttask.model;
import org.springframework.data.annotation.Id;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Document(collection = "customer")
public class Customer {  
    
    @Valid
    
    @Id
    private String _id;
    @Indexed(unique=true)
    @NotNull(message = " _username is mandatory")
    @NotBlank(message = " _username can't be empty")
    private String _username;
}

 

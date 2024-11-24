package com.example.quize2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User{

    @NotEmpty(message = "ID should not be empty")
    @Size(min = 2,message = "ID length should at least 2")
    private String ID;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,message = "name length should at least 2")
    private String name;

    @Positive(message = "age should be positive")
    @Min(value = 15,message = "user age should be at least 16")
    private int age;

    @NotNull(message = "balance should not be null")
    @PositiveOrZero(message = "balance should be positive or zero")
    private double balance;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "customer|librarian",message = "role should be  customer OR librarian")
    private String role;

}

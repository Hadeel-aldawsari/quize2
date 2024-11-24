package com.example.quize2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "ID should not be empty")
    @Size(min = 2,message = "ID length should at least 2")
    private String ID;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,message = "name length should at least 2")
    private String name;

    @NotNull(message = "number of pages should not be null")
    @Positive(message = "number og pages should be positive number")
    @Min(value = 2,message = "number of pages should be at least 2 pages")
    private int number_of_pages;

    @NotNull(message = "price should not be null")
    @PositiveOrZero(message = "price should be positive number or zero")
    private double price;

    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "novel|academic",message = "category should be novel OR academic ")
    private String category;

    @AssertTrue(message = "isAvailable should be true by default ")
    private boolean isAvailable;
}

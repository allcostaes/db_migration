package com.example.producer;
import lombok.Data;
@Data
public class ProductRequest {
    private String name;
    private String description;
    private int price;
}
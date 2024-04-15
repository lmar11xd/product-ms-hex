package com.lmar.productmicroservice.infraestructure.input.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDto {
    private String id;
    private String description;
    private double price;
    private int discount;
}

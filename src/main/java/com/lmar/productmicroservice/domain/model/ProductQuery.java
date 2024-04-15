package com.lmar.productmicroservice.domain.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductQuery {
    private String id;
    private String description;
    private double price;
    private int discount;
}

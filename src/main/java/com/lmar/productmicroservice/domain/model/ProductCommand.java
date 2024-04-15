package com.lmar.productmicroservice.domain.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductCommand {
    private String description;
    private double price;
    private int discount;
}

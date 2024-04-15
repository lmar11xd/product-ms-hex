package com.lmar.productmicroservice.infraestructure.util;

import com.lmar.productmicroservice.domain.model.ProductCommand;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.infraestructure.input.dto.ProductDto;
import com.lmar.productmicroservice.infraestructure.output.entity.Product;
import org.springframework.beans.BeanUtils;

public class ConverterUtil {
    public static ProductCommand toCommand(ProductDto productDto) {
        ProductCommand command = new ProductCommand();
        BeanUtils.copyProperties(productDto, command);
        return command;
    }

    public static ProductDto toDto(ProductCommand productCommand) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(productCommand, dto);
        return dto;
    }

    public static ProductQuery toQuery(ProductDto productDto) {
        ProductQuery query = new ProductQuery();
        BeanUtils.copyProperties(productDto, query);
        return query;
    }

    public static ProductDto toDto(ProductQuery productQuery) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(productQuery, dto);
        return dto;
    }

    public static Product toEntity(ProductCommand productCommand) {
        Product entity = new Product();
        BeanUtils.copyProperties(productCommand, entity);
        return entity;
    }

    public static Product toEntity(ProductQuery productQuery) {
        Product entity = new Product();
        BeanUtils.copyProperties(productQuery, entity);
        return entity;
    }

    public static ProductQuery toQuery(Product product) {
        ProductQuery query = new ProductQuery();
        BeanUtils.copyProperties(product, query);
        return query;
    }

    public static ProductCommand toCommand(Product product) {
        ProductCommand command = new ProductCommand();
        BeanUtils.copyProperties(product, command);
        return command;
    }

}

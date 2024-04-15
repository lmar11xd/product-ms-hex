package com.lmar.productmicroservice.infraestructure.input.controller;

import com.lmar.productmicroservice.application.create.ProductCreateUseCase;
import com.lmar.productmicroservice.application.find.ProductFindUseCase;
import com.lmar.productmicroservice.infraestructure.input.dto.ProductDto;
import com.lmar.productmicroservice.infraestructure.util.ConverterUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductCreateUseCase productCreateUseCase;

    @Autowired
    private ProductFindUseCase productFindUseCase;

    @GetMapping("all")
    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackAll")
    public Flux<ProductDto> all() {
        return productFindUseCase.findAllProducts().map(ConverterUtil::toDto);
    }

    @GetMapping("{id}")
    @CircuitBreaker(name = "getProductByIdCB", fallbackMethod = "fallBackGetProductById")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id) {
        return productFindUseCase.findProductById(id)
                .map(ConverterUtil::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @CircuitBreaker(name = "insertProductCB", fallbackMethod = "fallBackInsertProduct")
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDto) {
        return productCreateUseCase
                .createProduct(productDto.map(ConverterUtil::toCommand))
                .map(ConverterUtil::toDto);
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDto) {
        return productCreateUseCase
                .updateProduct(id, productDto.map(ConverterUtil::toCommand))
                .map(ConverterUtil::toDto);
    }

    @DeleteMapping("{id}")
    @CircuitBreaker(name = "deleteProductCB", fallbackMethod = "fallBackDeleteProduct")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productCreateUseCase.deleteProduct(id);
    }

    public Flux<ProductDto> fallBackAll(Throwable throwable) {
        return Flux.empty();
    }

    public Mono<ResponseEntity<ProductDto>> fallBackGetProductById(String id, Throwable throwable) {
        return Mono.empty();
    }

    public Mono<ProductDto> fallBackInsertProduct(Mono<ProductDto> productDto, Throwable throwable) {
        return Mono.empty();
    }

    public Mono<Void> fallBackDeleteProduct(String id, Throwable throwable) {
        return Mono.empty();
    }
}

package com.lmar.productmicroservice.application.find;

import com.lmar.productmicroservice.domain.exception.ProductNotFoundException;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import com.lmar.productmicroservice.infraestructure.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductFindUseCase {
    @Autowired
    private ProductQueryRepository productQueryRepository;

    public Flux<ProductQuery> findAllProducts() {
        return productQueryRepository.finAllProducts();
    }

    public Mono<ProductQuery> findProductById(String id) {
        return productQueryRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
    }

}

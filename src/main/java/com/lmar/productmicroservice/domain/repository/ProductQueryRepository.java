package com.lmar.productmicroservice.domain.repository;

import com.lmar.productmicroservice.domain.model.ProductQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductQueryRepository {
    Mono<ProductQuery> findById(String id);
    Flux<ProductQuery> finAllProducts();
}

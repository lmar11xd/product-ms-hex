package com.lmar.productmicroservice.domain.repository;

import com.lmar.productmicroservice.domain.model.ProductCommand;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import reactor.core.publisher.Mono;

public interface ProductCommandRepository {
    Mono<ProductQuery> createProduct(Mono<ProductCommand> productCommand);
    Mono<ProductQuery> updateProduct(String id, Mono<ProductCommand> productCommand);
    Mono<Void> deleteProduct(String id);
}

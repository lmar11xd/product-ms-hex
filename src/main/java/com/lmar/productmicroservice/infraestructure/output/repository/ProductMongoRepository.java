package com.lmar.productmicroservice.infraestructure.output.repository;

import com.lmar.productmicroservice.infraestructure.output.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductMongoRepository extends ReactiveMongoRepository<Product, String> {
    Mono<Product> findByDescription(String description);
}

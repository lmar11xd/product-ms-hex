package com.lmar.productmicroservice.application.find;

import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return productQueryRepository.findById(id);
    }

}

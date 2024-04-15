package com.lmar.productmicroservice.application.find;

import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductFindUseCase {
    public static final String MSG_RECOVER = "Fin intentos";

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 300),
            noRetryFor = IllegalStateException.class,
            notRecoverable = IllegalArgumentException.class
    )
    public Flux<ProductQuery> findAllProducts() {
        return productQueryRepository.finAllProducts();
    }

    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 300))
    public Mono<ProductQuery> findProductById(String id) {
        System.out.println("Intento: Buscar producto...");
        return productQueryRepository.findById(id);
    }

    @Recover
    public Flux<ProductQuery> recovery() {
        return Flux.empty();
    }

}

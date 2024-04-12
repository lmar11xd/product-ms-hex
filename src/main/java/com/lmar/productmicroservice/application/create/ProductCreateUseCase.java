package com.lmar.productmicroservice.application.create;

import com.lmar.productmicroservice.domain.model.ProductCommand;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductCreateUseCase {
    @Autowired
    private ProductCommandRepository productCommandRepository;

    public Mono<ProductQuery> createProduct(Mono<ProductCommand> productCommand) {
        return productCommandRepository.createProduct(productCommand);
    }

    public Mono<ProductQuery> updateProduct(String id, Mono<ProductCommand> productCommand) {
        return productCommandRepository.updateProduct(id, productCommand);
    }

    public Mono<Void> deleteProduct(String id) {
        return productCommandRepository.deleteProduct(id);
    }
}

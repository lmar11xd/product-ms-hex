package com.lmar.productmicroservice.application.create;

import com.lmar.productmicroservice.domain.model.ProductCommand;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductCommandRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductCreateUseCase {
    @Autowired
    private ProductCommandRepository productCommandRepository;

    @Autowired
    private KieSession kieSession;

    public Mono<ProductQuery> createProduct(Mono<ProductCommand> productCommand) {
        return productCommandRepository.createProduct(productCommand.map(this::getProductDiscount));
    }

    public Mono<ProductQuery> updateProduct(String id, Mono<ProductCommand> productCommand) {
        return productCommandRepository.updateProduct(id, productCommand);
    }

    public Mono<Void> deleteProduct(String id) {
        return productCommandRepository.deleteProduct(id);
    }

    private ProductCommand getProductDiscount(ProductCommand productCommand) {
        kieSession.insert(productCommand);
        kieSession.fireAllRules();
        return productCommand;
    }
}

package com.lmar.productmicroservice.infraestructure;

import com.lmar.productmicroservice.domain.exception.ProductNotFoundException;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import com.lmar.productmicroservice.infraestructure.output.repository.ProductMongoRepository;
import com.lmar.productmicroservice.infraestructure.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    @Autowired
    private ProductMongoRepository productMongoRepository;

    @Override
    public Mono<ProductQuery> findById(String id) {
        return this.productMongoRepository.findById(id)
                .map(ConverterUtil::toQuery);
    }

    @Override
    public Flux<ProductQuery> finAllProducts() {
        return this.productMongoRepository.findAll()
                .map(ConverterUtil::toQuery);
    }
}
package com.lmar.productmicroservice.infraestructure;

import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import com.lmar.productmicroservice.infraestructure.exception.CustomException;
import com.lmar.productmicroservice.infraestructure.output.repository.ProductMongoRepository;
import com.lmar.productmicroservice.infraestructure.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .map(ConverterUtil::toQuery)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
    }

    @Override
    public Flux<ProductQuery> finAllProducts() {
        return this.productMongoRepository.findAll()
                .map(ConverterUtil::toQuery);
    }
}

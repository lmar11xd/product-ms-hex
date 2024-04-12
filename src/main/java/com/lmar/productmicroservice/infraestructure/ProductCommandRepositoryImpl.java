package com.lmar.productmicroservice.infraestructure;

import com.lmar.productmicroservice.domain.exception.ProductNotFoundException;
import com.lmar.productmicroservice.domain.model.ProductCommand;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductCommandRepository;
import com.lmar.productmicroservice.infraestructure.output.repository.ProductMongoRepository;
import com.lmar.productmicroservice.infraestructure.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    @Autowired
    private ProductMongoRepository productMongoRepository;

    @Override
    public Mono<ProductQuery> createProduct(Mono<ProductCommand> productCommand) {
        return productCommand.map(ConverterUtil::toEntity)
                .flatMap(productMongoRepository::save)
                .map(ConverterUtil::toQuery);
    }

    @Override
    public Mono<ProductQuery> updateProduct(String id, Mono<ProductCommand> productCommand) {
        Mono<Boolean> productId = this.productMongoRepository.findById(id).hasElement();

        return productId.flatMap(existsId -> existsId ?
                this.productMongoRepository.findById(id)
                        .flatMap(product -> productCommand.map(ConverterUtil::toEntity))
                        .doOnNext(e -> e.setId(id))
                        .flatMap(this.productMongoRepository::save)
                        .map(ConverterUtil::toQuery)
                : Mono.error(ProductNotFoundException::new));
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        Mono<Boolean> productId = this.productMongoRepository.findById(id).hasElement();
        return productId.flatMap(exists -> exists
                ? this.productMongoRepository.deleteById(id)
                : Mono.error(ProductNotFoundException::new));
    }
}

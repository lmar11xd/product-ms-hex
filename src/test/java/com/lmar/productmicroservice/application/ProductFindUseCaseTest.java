package com.lmar.productmicroservice.application;

import com.lmar.productmicroservice.application.find.ProductFindUseCase;
import com.lmar.productmicroservice.domain.model.ProductQuery;
import com.lmar.productmicroservice.domain.repository.ProductQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductFindUseCaseTest {

    @MockBean
    private ProductQueryRepository productQueryRepositoryMock;

    @Autowired
    private ProductFindUseCase productFindUseCase;

    @Test
    void testSuccessFindAllProducts() {
        when(productQueryRepositoryMock.finAllProducts())
                .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST))
                .thenReturn(Flux.empty());
        assertEquals(Flux.empty(), productFindUseCase.findAllProducts());
        verify(productQueryRepositoryMock, times(2)).finAllProducts();
    }

    @Test
    void testSuccessAfterOneException() {
        when(productQueryRepositoryMock.finAllProducts())
                .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST))
                .thenReturn(Flux.empty());
        assertEquals(Flux.empty(), productFindUseCase.findAllProducts());
        verify(productQueryRepositoryMock, times(2)).finAllProducts();
    }

    @Test
    void testRecoverAfterTwoExceptions() {
        when(productQueryRepositoryMock.finAllProducts())
                .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST))
                .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST));

        assertEquals(Flux.empty(), productFindUseCase.findAllProducts());
        verify(productQueryRepositoryMock, times(2)).finAllProducts();
    }

    @Test
    void testNoRetry() {
        when(productQueryRepositoryMock.finAllProducts())
                .thenThrow(new IllegalStateException());

        assertEquals(Flux.empty(), productFindUseCase.findAllProducts());
        verify(productQueryRepositoryMock, times(1)).finAllProducts();
    }

    @Test
    void testRecover() {
        when(productQueryRepositoryMock.finAllProducts())
                .thenThrow(new IllegalArgumentException())
                .thenThrow(new IllegalArgumentException())
                .thenReturn(Flux.empty());

        assertThrows(IllegalArgumentException.class, () -> productFindUseCase.findAllProducts());
        verify(productQueryRepositoryMock, times(2)).finAllProducts();
    }

    @Test
    void testSuccessFindProductById() {
        String id = "abcd";
        ProductQuery product = new ProductQuery();
        product.setId(id);
        when(productQueryRepositoryMock.findById(id))
                .thenThrow(new HttpServerErrorException(HttpStatus.BAD_REQUEST))
                .thenReturn(Mono.just(product));

        Mono<ProductQuery> currentProduct = productFindUseCase.findProductById(id);
        assertEquals(id, currentProduct.block().getId());
        verify(productQueryRepositoryMock, times(2)).findById(id);
    }
}

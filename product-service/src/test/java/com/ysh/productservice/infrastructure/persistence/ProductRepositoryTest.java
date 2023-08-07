package com.ysh.productservice.infrastructure.persistence;

import com.ysh.productservice.domain.product.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void test_save() {
        //given
        Product product = new Product("Test", "TestDescription", 3000L, 3000);

        //when
        productRepository.save(product);

        //then
        Optional<Product> findOpProduct = productRepository.findById(product.getId());
        Assertions.assertTrue(findOpProduct.isPresent());

        Product findProduct = findOpProduct.get();
        Assertions.assertEquals(product.getId(), findProduct.getId());
        Assertions.assertEquals(product.getName(), findProduct.getName());
        Assertions.assertEquals(product.getDescription(), findProduct.getDescription());
        Assertions.assertEquals(product.getPrice(), findProduct.getPrice());
        Assertions.assertEquals(product.getStockCnt(), findProduct.getStockCnt());
    }


}
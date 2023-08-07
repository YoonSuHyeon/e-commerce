package com.ysh.productservice.domain.product.service;

import com.ysh.productservice.domain.product.entity.Product;
import com.ysh.productservice.infrastructure.persistence.ProductRepository;
import com.ysh.productservice.presentation.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest request) {

        //유저가 상품을 생성 할 수 있는지 확인

        //요청 값 체크
        Product newProduct = request.toProduct();


    }
}

package com.ysh.productservice.presentation.controller;

import com.ysh.productservice.domain.product.service.ProductService;
import com.ysh.productservice.presentation.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //TODO 상품 등록
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.ok().build();
    }

    //TODO 상품 조회
    //TODO 상품 수정
}

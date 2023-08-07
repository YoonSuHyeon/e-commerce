package com.ysh.productservice.presentation.dto;


import com.ysh.productservice.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Long price;
    private int stockCnt;

    public Product toProduct() {
        return new Product(name, description, price, stockCnt);
    }
}

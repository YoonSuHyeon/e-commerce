package com.ysh.productservice.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    private int stockCnt;

    public Product(String name, String description, Long price, int stockCnt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockCnt = stockCnt;
    }
}

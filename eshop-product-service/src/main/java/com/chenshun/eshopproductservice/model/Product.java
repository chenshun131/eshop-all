package com.chenshun.eshopproductservice.model;

import lombok.Data;

@Data
public class Product {

    private Long id;

    private String name;

    private Long categoryId;

    private Long brandId;

}

package com.gridu.store.model;

import com.gridu.store.DTO.BasketProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;

    @OneToOne(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    private Stock stock;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
}



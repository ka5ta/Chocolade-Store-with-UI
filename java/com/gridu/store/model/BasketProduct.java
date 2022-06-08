package com.gridu.store.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="basket_products")
public class BasketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;
    private int quantity;


}

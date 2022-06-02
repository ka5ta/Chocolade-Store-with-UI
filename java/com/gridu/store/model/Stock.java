package com.gridu.store.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @MapsId
    private Product product;
    private int quantity;

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productId=" + product.getId() +
                ", quantity=" + quantity +
                '}';
    }
}

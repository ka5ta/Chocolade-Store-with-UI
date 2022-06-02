package com.gridu.store.model;

import com.gridu.store.DTO.BasketProductDTO;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double  price;
    @OneToOne(mappedBy = "product")
    private Stock stock;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockId=" + stock.getId() +
                '}';
    }
}

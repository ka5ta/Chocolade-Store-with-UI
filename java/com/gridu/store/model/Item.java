package com.gridu.store.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name="items")
public class  Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double  price;
    @OneToOne(mappedBy = "item")
    private Stock stock;

}

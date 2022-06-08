package com.gridu.store.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts;

    public Order() {
    }

    public Order(Account account, List<OrderProduct> orderProducts) {
        this.account = account;
        this.orderProducts = orderProducts;
    }

    public Order(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", account=" + account +
                ", orderProducts=" + orderProducts +
                '}';
    }
}

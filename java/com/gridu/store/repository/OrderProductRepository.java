package com.gridu.store.repository;


import com.gridu.store.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {

    List<OrderProduct> findAll();

}

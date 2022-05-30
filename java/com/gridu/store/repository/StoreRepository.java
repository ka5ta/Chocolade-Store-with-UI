package com.gridu.store.repository;

import com.gridu.store.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();
}

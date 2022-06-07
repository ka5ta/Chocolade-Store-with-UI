package com.gridu.store.DTO;

import lombok.Data;


public interface BasketProductDTO {



    Long getProductId();
    Integer getStockQuantity();
    Integer getBasketQuantity();
    Long getBasketProductId();

    String getBasketProductPrice();
}

package com.gridu.store.dto;


import lombok.Data;

@Data
public class BasketProductPostDTO {

    private Integer basketQuantity;
    private Long accountId;
    private Long productId;
    private Long basketProductId;

}

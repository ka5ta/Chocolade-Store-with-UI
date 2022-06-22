package com.gridu.store.dto;

import lombok.Data;


@Data
public class BasketProductGetDTO {

    private Long productId;
    private Long accountId;
    private String productName;
    private double productPrice;
    private Integer stockQuantity;
    private Long basketProductId;
    private Integer basketQuantity;
    private String basketProductPrice;


    public String getBasketProductPrice(){
        if(this.basketQuantity != null){
            double basketProductPrice = this.productPrice * this.basketQuantity;
            this.basketProductPrice = String.format("%.2f PLN", basketProductPrice);
        }
        return this.basketProductPrice;
    }

}

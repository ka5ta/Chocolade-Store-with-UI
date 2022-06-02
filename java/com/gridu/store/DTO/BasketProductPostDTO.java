package com.gridu.store.DTO;


import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

@Data
public class BasketProductPostDTO {

    private Integer basketQuantity;
    private Long accountId;
    private Long productId;
    private Long basketProductId;

}

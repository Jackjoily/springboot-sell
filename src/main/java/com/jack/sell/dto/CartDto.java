package com.jack.sell.dto;

import lombok.Data;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

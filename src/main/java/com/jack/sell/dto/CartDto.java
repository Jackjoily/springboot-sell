package com.jack.sell.dto;

import lombok.Data;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description  从前台传过来的购物车购物车中只包含商品名称，以及商品数量
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

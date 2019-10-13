package com.jack.sell.enums;

import lombok.Getter;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXISTS(10,"商品不存在"),;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

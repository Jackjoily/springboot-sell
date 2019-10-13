package com.jack.sell.enums;

import lombok.Getter;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXISTS(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(14,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

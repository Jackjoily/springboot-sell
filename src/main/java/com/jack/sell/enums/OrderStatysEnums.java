package com.jack.sell.enums;

import lombok.Getter;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Getter
public enum  OrderStatysEnums implements CodeEnum {
NEW(0,"新订单"),
FINSHED(1,"完结"),
CANCEL(2,"已取消");
private Integer code;
private String msg;

    OrderStatysEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

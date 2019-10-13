package com.jack.sell.exception;

import com.jack.sell.enums.ResultEnum;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}

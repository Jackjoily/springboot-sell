package com.jack.sell.exception;

import com.jack.sell.enums.ResultEnum;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;

    }
}

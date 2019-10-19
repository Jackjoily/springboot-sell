package com.jack.sell.handler;

import com.jack.sell.VO.ResultVo;
import com.jack.sell.exception.SellException;
import com.jack.sell.utils.ResultVoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-18 13:07
 */
@ControllerAdvice
//@advice是aop中的方法，被标注了@ControllerAdvice的方法都会在切入@RequestMaping注解的方法中去
public class SellExceptionHanlder {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e) {
        return ResultVoUtils.error(e.getCode(), e.getMessage());
    }
}
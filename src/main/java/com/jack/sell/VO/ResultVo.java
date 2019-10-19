package com.jack.sell.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 *
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = -6358477745055616413L;
    private Integer code;
    private String msg;
    //返回的具体内容
    private T data;
}

package com.jack.sell.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    //返回的具体内容
    private T data;
}

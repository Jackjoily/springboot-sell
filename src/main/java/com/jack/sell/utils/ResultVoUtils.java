package com.jack.sell.utils;

import com.jack.sell.VO.ResultVo;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public class ResultVoUtils {
    public static ResultVo successs(Object obj) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(obj);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success() {

        return successs(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg("msg");
        return resultVo;
    }
}

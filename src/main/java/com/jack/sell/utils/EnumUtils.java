package com.jack.sell.utils;

import com.jack.sell.enums.CodeEnum;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-16 15:23
 */
public class EnumUtils {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
package com.jack.sell.utils;

import java.util.Random;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqekey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}

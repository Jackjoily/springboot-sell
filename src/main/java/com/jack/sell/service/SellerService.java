package com.jack.sell.service;

import com.jack.sell.dataobject.SellerInfo;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 17:10
 */

public interface SellerService {

SellerInfo findSellerInfoByopenid(String openid);
}
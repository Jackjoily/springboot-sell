package com.jack.sell.service;

import com.jack.sell.dto.OrderDto;

public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openid, String orderid);

    //取消订单
    OrderDto cancelOrder(String openid, String orderid);


}

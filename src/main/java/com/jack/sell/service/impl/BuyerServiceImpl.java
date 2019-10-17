package com.jack.sell.service.impl;

import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.service.BuyerService;
import com.jack.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-14 11:36
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderid) {
        return  checkOrderOwne(openid, orderid);
    }



    @Override
    public OrderDto cancelOrder(String openid, String orderid) {
        OrderDto orderDto = checkOrderOwne(openid, orderid);
        if(orderDto==null){
            log.error("【取消订单】查询不到该订单", orderid);
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }


    private OrderDto checkOrderOwne(String openid, String orderid) {
        OrderDto orderDto = orderService.findOne(orderid);
        if (orderDto != null) {
            if (!orderDto.getBuyerOpenid().equals(openid)) {
                log.error("【查询订单】 订单的openid不一致 ，openid={}", openid);
                throw new SellException(ResultEnum.ORDER_OWENER_ERROR);
            }

        } else {
            return null;
        }
        return orderDto;
    }
}
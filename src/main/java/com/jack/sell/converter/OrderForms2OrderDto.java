package com.jack.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jack.sell.dataobject.OrderDetail;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-14 09:47
 */
@Slf4j
public class OrderForms2OrderDto {

    public  static OrderDto convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换错误】，string={}", orderForm.getItems());
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return  orderDto;
    }
}
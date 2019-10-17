package com.jack.sell.controller;

import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-16 11:24
 * 提醒网址//pay notify
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //这里只是模拟发起支付
        OrderDto paid = orderService.paid(orderDto);
        //将订单的支付状态设置成为已支付状态
        map.put("orderId", orderId);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public void notify(@RequestBody String notidyData ){

    }


}
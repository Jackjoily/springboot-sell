package com.jack.sell.controller;

import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * @program: sell
 * @description: 卖家端的订单
 * @author: jackjoily
 * @create: 2019-10-16 14:48
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Map<String, Object> map
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDto> dtos = orderService.findList(pageRequest);
        map.put("dtos", dtos);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map
    ) {
        try {
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        } catch (Exception e) {
            log.error("【卖家取消订单发生异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
    @GetMapping("/detail")
    public ModelAndView detail(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map
    ) {
        OrderDto dtos = new OrderDto();
        try {
            dtos = orderService.findOne(orderId);
        } catch (Exception e) {
            log.error("【卖家取消订单发生异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("dtos", dtos);
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(
            @RequestParam("orderId") String orderId,
            Map<String, Object> map) {
        OrderDto dtos = new OrderDto();
        try {
            dtos = orderService.findOne(orderId);
            orderService.finsh(dtos);
        } catch (Exception e) {
            log.error("【卖家端完结订单发生异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
}
package com.jack.sell.service.impl;

import com.jack.sell.dataobject.OrderDetail;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.OrderStatysEnums;
import com.jack.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private final String buyerOpenId = "110110";
    private final String ORDER_ID = "1570963209550203629";

    @Test
    public void crearte() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("廖师兄");
        orderDto.setBuyerAddress("慕课网");
        orderDto.setBuyerPhone("12345678933");
        orderDto.setBuyerOpenid(buyerOpenId);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123458");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);
        orderDetailList.add(o1);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto resutlt = orderService.crearte(orderDto);
        log.info("创建订单result={}" + resutlt);
    }

    @Test
    public void findOne() {
        OrderDto one = orderService.findOne("1570963209550203629");
        assertEquals(ORDER_ID, one.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest=PageRequest.of(0, 2);
        Page<OrderDto> list = orderService.findList(buyerOpenId, pageRequest);
        assertNotEquals(0, list.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto one = orderService.findOne("1570963209550203629");
        OrderDto cancel = orderService.cancel(one);
        assertEquals(OrderStatysEnums.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finsh() {
    }

    @Test
    public void paid() {
    }
}
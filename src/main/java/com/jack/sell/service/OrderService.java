package com.jack.sell.service;

import com.jack.sell.dataobject.OrderMaster;
import com.jack.sell.dto.OrderDto;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDto crearte(OrderDto orderDto);
    /**
     * 查询dange订单
     */
    OrderDto findOne(String orderId);

    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDto cancel(OrderDto orderDto);

    /**
     * 完结订单
     */
    OrderDto finsh(OrderDto orderDto);

    /**
     * 支付订单
     */
    OrderDto paid(OrderDto orderDto);

}

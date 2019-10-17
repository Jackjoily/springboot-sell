package com.jack.sell.service;

import com.jack.sell.dto.OrderDto;

public interface PayService {
    void create(OrderDto orderDto);
}

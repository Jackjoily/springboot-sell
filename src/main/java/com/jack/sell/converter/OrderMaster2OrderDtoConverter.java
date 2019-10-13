package com.jack.sell.converter;

import com.jack.sell.dataobject.OrderMaster;
import com.jack.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description:
 * @author: liu yan
 * @create: 2019-10-13 19:02
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        )
                .collect(Collectors.toList());

    }
}
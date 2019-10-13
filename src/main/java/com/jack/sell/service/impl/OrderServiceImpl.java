package com.jack.sell.service.impl;

import com.jack.sell.dataobject.OrderDetail;
import com.jack.sell.dataobject.OrderMaster;
import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.repository.OrderDetailRepository;
import com.jack.sell.repository.OrderMasterRepository;
import com.jack.sell.service.OrderService;
import com.jack.sell.service.ProductService;
import com.jack.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDto crearte(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqekey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1.查询商品的数量，价格
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            //2.计算订单总价
            orderAmount = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.写入订单数据库(orderdetail)
            orderDetail.setDetailId(KeyUtil.genUniqekey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库 ordermaster
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        return null;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finsh(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}

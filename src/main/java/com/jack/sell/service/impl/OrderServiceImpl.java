package com.jack.sell.service.impl;

import com.jack.sell.converter.OrderMaster2OrderDtoConverter;
import com.jack.sell.dataobject.OrderDetail;
import com.jack.sell.dataobject.OrderMaster;
import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.CartDto;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.OrderStatysEnums;
import com.jack.sell.enums.PayStatusEnum;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.repository.OrderDetailRepository;
import com.jack.sell.repository.OrderMasterRepository;
import com.jack.sell.service.OrderService;
import com.jack.sell.service.ProductService;
import com.jack.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    @Transactional
    public OrderDto crearte(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqekey();
//
        BigDecimal orderAmount = new BigDecimal(0);
        //1.查询商品的数量，价格
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.写入订单数据库(orderdetail)
            orderDetail.setDetailId(KeyUtil.genUniqekey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
//            CartDto cartDto = new CartDto(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDtoList.add(cartDto);
        }
        //3.写入订单数据库 ordermaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatysEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtolist = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtolist, pageable, orderMasterPage.getTotalElements());
        return orderDtoPage;
    }
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //先判断订单的状态
        if (!orderDto.getOrderStatus().equals(OrderStatysEnums.NEW.getCode())) {
            log.error("[取消订单] 订单状态不正确 orderId={},orderSatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatysEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster orderMasterUpadte = orderMasterRepository.save(orderMaster);
        if (orderMasterUpadte == null) {
            log.error("[取消订单] 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中五商品详情，orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().
                map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        //如果已支付，需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
         //TODO

        }
        return orderDto;
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

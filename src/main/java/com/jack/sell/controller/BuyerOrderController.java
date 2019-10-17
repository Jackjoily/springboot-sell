package com.jack.sell.controller;

import com.jack.sell.VO.ProductInfoVo;
import com.jack.sell.VO.ProductVo;
import com.jack.sell.VO.ResultVo;
import com.jack.sell.converter.OrderForms2OrderDto;
import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.form.OrderForm;
import com.jack.sell.service.BuyerService;
import com.jack.sell.service.CategoryService;
import com.jack.sell.service.OrderService;
import com.jack.sell.service.ProductService;
import com.jack.sell.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(
            @Valid OrderForm orderForm, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单不正确】参数不正确,oderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForms2OrderDto.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单不正确】购物车不能为空", orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto crearteresult = orderService.crearte(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", crearteresult.getOrderId());
        return ResultVoUtils.successs(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(
            @RequestParam("openid") String openid,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openId为空", openid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDto> orderPageDto = orderService.findList(openid, pageRequest);
        return ResultVoUtils.successs(orderPageDto.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(
            @RequestParam("openid") String openid,
            @RequestParam("orderId") String orderId
    ) {
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtils.successs(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(
            @RequestParam("openid") String openid,
            @RequestParam("orderid") String orderId
    ) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtils.successs();
    }
}


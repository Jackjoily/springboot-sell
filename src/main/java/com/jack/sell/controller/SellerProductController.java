package com.jack.sell.controller;

import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.service.ProductService;
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
 * @description: 卖家端商品
 * @author: jackjoily
 * @create: 2019-10-16 19:13
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Map<String, Object> map
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> pageLists = productService.findAll(pageRequest);
        map.put("pageLists", pageLists);
        map.put("currentPage", page);
        return new ModelAndView("product/list", map);
    }

    /**
     * 上架
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView on_sale(
            @RequestParam(value = "orderId") String orderId,
            Map<String, Object> map
    ) {
        try {
            productService.onSale(orderId);
        } catch (Exception e) {
            log.error("【卖家端上架异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_MODIFY_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 下架
     * @param orderId
     * @param map
     * @return
     */

    @GetMapping("/off_sale")
    public ModelAndView off_sale(
            @RequestParam(value = "orderId") String orderId,
            Map<String, Object> map
    ) {
        try {
            productService.offSale(orderId);
        } catch (Exception e) {
            log.error("【卖家端下架异常】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_MODIFY_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

}
package com.jack.sell.controller;

import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.OrderDto;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.form.ProductForm;
import com.jack.sell.service.CategoryService;
import com.jack.sell.service.ProductService;
import com.jack.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private CategoryService categoryService;

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
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView on_sale(
            @RequestParam(value = "productId") String productId,
            Map<String, Object> map
    ) {
        try {
            productService.onSale(productId);
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
     *
     * @param productId
     * @param map
     * @return
     */

    @GetMapping("/off_sale")
    public ModelAndView off_sale(
            @RequestParam(value = "productId") String productId,
            Map<String, Object> map
    ) {
        try {
            productService.offSale(productId);
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

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 保存更新
     *
     * @param form
     * @param bindResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    //将返回的内容写到redis中去@CachePut
    //CacheEvict驱逐清除缓存
    @CacheEvict(cacheNames = "product",key="123")
    public ModelAndView save(
            @Valid ProductForm form,
            BindingResult bindResult,
            Map<String, Object> map
    ) {
        if (bindResult.hasErrors()) {
            map.put("msg", bindResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo=new ProductInfo();
        try {
            //商品id为空则为新增商品
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
                BeanUtils.copyProperties(form, productInfo);
            }else{
                BeanUtils.copyProperties(form, productInfo);
                productInfo.setProductId(KeyUtil.genUniqekey());
            }
            productService.save(productInfo);
        } catch (Exception e) {
            log.error("【卖家端怎加商品】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        map.put("msg", ResultEnum.PRODUCT_MODIFY_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }


}
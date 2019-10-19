package com.jack.sell.controller;

import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.form.CategoryForm;
import com.jack.sell.form.ProductForm;
import com.jack.sell.service.CategoryService;
import com.jack.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 10:48
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Map<String, Object> map
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductCategory> categoryList = categoryService.findAll(pageRequest);
        map.put("categoryList", categoryList);
        map.put("currentPage", page);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            Map<String, Object> map
    ) {
        ProductCategory category = new ProductCategory();
        try {
            if (!StringUtils.isEmpty(categoryId)) {
                category = categoryService.findOne(categoryId);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        map.put("category", category);
        return new ModelAndView("category/index");
    }

    @PostMapping("/save")
    public ModelAndView save(
            @Valid CategoryForm form,
            BindingResult bindResult,
            Map<String, Object> map
    ) {

        if (bindResult.hasErrors()) {
            map.put("msg", bindResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller//index");
            return new ModelAndView("common/error", map);
        }
        //商品id为空则为新增商品
        ProductCategory productCategory = new ProductCategory();
        try {
            if (!StringUtils.isEmpty(form.getCategoryId())) {
                productCategory = categoryService.findOne(form.getCategoryId());
                BeanUtils.copyProperties(form, productCategory);
            } else {
                BeanUtils.copyProperties(form, productCategory);
            }
            categoryService.save(productCategory);
        } catch (Exception e) {
            log.error("【卖家端修改目录】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        map.put("msg", ResultEnum.CATEGORY_MODIFY_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}
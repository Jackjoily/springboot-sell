package com.jack.sell.controller;

import com.jack.sell.VO.ProductInfoVo;
import com.jack.sell.VO.ProductVo;
import com.jack.sell.VO.ResultVo;
import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.service.CategoryService;
import com.jack.sell.service.ProductService;
import com.jack.sell.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list() {
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpall();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        //精简方法
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVo> productVoList=new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategorytype(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVos);
            productVoList.add(productVo);
        }
        return ResultVoUtils.successs(productVoList);
    }
}

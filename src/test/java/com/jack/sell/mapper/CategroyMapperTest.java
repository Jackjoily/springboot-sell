package com.jack.sell.mapper;

import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.dataobject.mapper.ProductCategroyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-18 13:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategroyMapperTest {
    @Autowired
    private ProductCategroyMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "我最喜欢吃的东西");
        map.put("category_type", 110);
        int i = mapper.insertByMap(map);
        System.out.println(i);
    }

    @Test
    public void insertByMapWithObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我最不喜欢吃的东西");
        productCategory.setCategoryType(121);
        mapper.insertByMapWithObject(productCategory);
    }

    @Test
    public void selectByTag() {
        ProductCategory productCategory = mapper.getproductCategory(2);
        System.out.println(productCategory);
    }

    @Test
    public void updatetproductCategory() {
        ProductCategory productCategory = mapper.getproductCategory(2);
        productCategory.setCategoryName("世界上最最最美味的东西");
        mapper.updatetproductCategory(productCategory);
    }


}
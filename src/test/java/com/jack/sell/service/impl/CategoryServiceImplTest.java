package com.jack.sell.service.impl;

import com.jack.sell.dataobject.ProductCategory;
import com.jack.sell.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() {
        ProductCategory one = categoryService.findOne(10);
        assertEquals(new Integer(10), one.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        assertNotEquals(new Integer(0), new Integer(all.size()));
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<ProductCategory> in = categoryService.findByCategoryTypeIn(integers);
        assertNotEquals(new Integer(0), new Integer(in.size()));
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory p=new ProductCategory("男生专享",10);
        this.categoryService.save(p);

    }
}
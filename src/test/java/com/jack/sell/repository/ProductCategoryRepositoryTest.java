package com.jack.sell.repository;

import com.jack.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory=repository.findById(1);
        System.out.println(productCategory);
    }
    //在测试类方法中加入事物，在方法完成之后会自动回滚
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory("女生最爱",4);
        ProductCategory result=repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null, result);
    }

    @Test
    @Transactional
    public void updateTest(){
        ProductCategory productCategory=repository.findById(2).get();
        productCategory.setCategoryType(5);
        repository.save(productCategory);
    }
    @Test
    public void findByCategoryTypeIn(){
        List<Integer> categoryTypeList = Arrays.asList(2,3,4);
        List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }
}
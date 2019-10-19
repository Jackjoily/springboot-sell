package com.jack.sell.repository;

import com.jack.sell.dataobject.SellerInfo;
import com.jack.sell.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellInfoRepositoryTest {

    @Autowired
    private SellInfoRepository repository;

    @Test
    public void findByOpneid() {
        SellerInfo abc = repository.findByOpenid("abc");
        assertNotEquals(null, abc);
    }

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqekey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo save = repository.save(sellerInfo);
        assertNotEquals(null, save);
    }


}
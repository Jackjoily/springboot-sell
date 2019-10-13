package com.jack.sell.repository;

import com.jack.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private final String OPENID = "110110";
    @Autowired
    OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1333683396");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster save = repository.save(orderMaster);
        assertNotEquals(null, save);
    }

    @Test
    public void findByBuyerOpenid() {
        OrderMaster orderMaster = new OrderMaster();
        Pageable pageable = PageRequest.of(0, 3);
        Example<OrderMaster> example = Example.of(orderMaster);
        Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid(OPENID, pageable);
        assertNotEquals(0, byBuyerOpenid.getContent().size());

    }
}
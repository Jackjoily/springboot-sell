package com.jack.sell.repository;

import com.jack.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("12345678910");
        orderDetail.setOrderId("11111112");
        orderDetail.setProductIcon("hssss.jpg");
        orderDetail.setProductId("111111111");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(3);
        OrderDetail save = repository.save(orderDetail);
        assertNotNull(save);
    }
    @Test
    public void findByOrderId(){
        List<OrderDetail> byOrderId = repository.findByOrderId("11111111111");
        assertNotEquals(0, byOrderId.size());
    }


}
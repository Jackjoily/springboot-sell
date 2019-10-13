package com.jack.sell.repository;

import com.jack.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
List<OrderDetail> findByOrderId(String orderId);
}

package com.jack.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description 订单详情表
 **/
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;

    private String productId;
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;

    private Integer productQuantity;
    private String productIcon;
}

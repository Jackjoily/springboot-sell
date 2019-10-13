package com.jack.sell.dto;

import com.jack.sell.dataobject.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Data
public class OrderDto {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    /**
     * 买家的微信Id
     */
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /**
     * 订单状态,默认为新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态,默认为0未支付
     */
    private Integer payStatus;
    //创建时间
    private Date create_time;
    //更新时间
    private Date update_time;
    private List<OrderDetail> orderDetailList;
}

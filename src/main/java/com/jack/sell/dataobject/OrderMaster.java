package com.jack.sell.dataobject;

import com.jack.sell.enums.OrderStatysEnums;
import com.jack.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
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
    private Integer orderStatus = OrderStatysEnums.NEW.getCode();
    /**
     * 支付状态,默认为0未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    private Date create_time;
    //更新时间
    private Date update_time;


}

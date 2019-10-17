package com.jack.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jack.sell.enums.ProductStatusEnum;
import com.jack.sell.utils.EnumUtils;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description 商品信息表
 **/
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /**
     * 0正常，1下架
     */
    private Integer productStatus;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtils.getByCode(productStatus, ProductStatusEnum.class);

    }
}

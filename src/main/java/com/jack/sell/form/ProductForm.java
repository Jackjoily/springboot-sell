package com.jack.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 10:01
 */
@Data
public class ProductForm {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
}
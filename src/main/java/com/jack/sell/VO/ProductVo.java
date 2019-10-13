package com.jack.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description 商品（包含类目）
 **/
@Data
public class ProductVo {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categorytype;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}

package com.jack.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description 商品（包含类目）
 **/
@Data
public class ProductVo implements Serializable {
    private static final long serialVersionUID = 7104586262397848930L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categorytype;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}

package com.example.takeout.modules.common.dto;

import com.example.takeout.modules.order.entity.OrderDetail;
import com.example.takeout.modules.order.entity.Orders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/28
 */
@Data
@ApiModel(value="订单传输对象")
public class OrdersDTO extends Orders {

    /**
     * 订单详情
     */
    @ApiModelProperty(value = "订单详情")
    private List<OrderDetail> orderDetails;

    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数")
    private Integer total;

}

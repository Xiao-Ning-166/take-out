package com.example.takeout.modules.common.dto;

import com.example.takeout.modules.order.entity.OrderDetail;
import com.example.takeout.modules.order.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/28
 */
@Data
public class OrdersDTO extends Orders {

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetails;

    /**
     * 商品总数
     */
    private Integer total;

}

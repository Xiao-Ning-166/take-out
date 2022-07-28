package com.example.takeout.modules.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.common.dto.OrdersDTO;
import com.example.takeout.modules.order.entity.Orders;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
public interface IOrdersService extends IService<Orders> {

    /**
     * 查询订单数据
     *
     * @param page
     * @return
     */
    IPage<OrdersDTO> listOrders(IPage<OrdersDTO> page);

    /**
     * 查询用户最新订单
     *
     * @return
     */
    OrdersDTO getLastOrder();

    /**
     * 添加一个订单
     *
     * @param order
     */
    void add(Orders order);
}

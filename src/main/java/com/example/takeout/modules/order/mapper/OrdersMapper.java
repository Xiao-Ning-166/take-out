package com.example.takeout.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.takeout.modules.common.dto.OrdersDTO;
import com.example.takeout.modules.order.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 得到最新的订单
     *
     * @param userId
     * @return
     */
    OrdersDTO getLastOrder(Long userId);

    /**
     * 查询订单数据
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<OrdersDTO> listOrders(IPage<OrdersDTO> page, Long userId);
}

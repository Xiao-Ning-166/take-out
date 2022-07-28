package com.example.takeout.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeout.modules.order.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}

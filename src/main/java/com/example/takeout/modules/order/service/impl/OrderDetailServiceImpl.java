package com.example.takeout.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.order.entity.OrderDetail;
import com.example.takeout.modules.order.mapper.OrderDetailMapper;
import com.example.takeout.modules.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
}

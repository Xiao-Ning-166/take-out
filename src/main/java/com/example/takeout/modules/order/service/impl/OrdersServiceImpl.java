package com.example.takeout.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.exception.TakeOutException;
import com.example.takeout.modules.address.entity.Address;
import com.example.takeout.modules.address.service.IAddressService;
import com.example.takeout.modules.common.dto.OrdersDTO;
import com.example.takeout.modules.order.entity.Orders;
import com.example.takeout.modules.order.entity.OrderDetail;
import com.example.takeout.modules.order.mapper.OrdersMapper;
import com.example.takeout.modules.order.service.IOrderDetailService;
import com.example.takeout.modules.order.service.IOrdersService;
import com.example.takeout.modules.shoppingCart.entity.ShoppingCart;
import com.example.takeout.modules.shoppingCart.service.IShoppingCartService;
import com.example.takeout.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询订单数据
     *
     * @param page
     * @return
     */
    @Override
    public IPage<OrdersDTO> listOrders(IPage<OrdersDTO> page) {
        // 1、获取用户id
        Long userId = BaseContext.getLoginUserId();

        return ordersMapper.listOrders(page, userId);
    }

    /**
     * 查询用户最新订单
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrdersDTO getLastOrder() {
        // 1、获取用户id
        Long userId = BaseContext.getLoginUserId();

        OrdersDTO lastOrder = ordersMapper.getLastOrder(userId);

        // 2、计算商品总数
        AtomicInteger total = new AtomicInteger();

        List<OrderDetail> orderDetails = lastOrder.getOrderDetails();

        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new TakeOutException("该订单空空如也!");
        }

        orderDetails.stream().map(orderDetail -> {
            total.addAndGet(orderDetail.getNumber());

            return orderDetail;
        }).collect(Collectors.toList());

        lastOrder.setTotal(total.get());

        return lastOrder;
    }

    /**
     * 添加一个订单
     *
     * @param order
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Orders order) {
        // 1、设置用户id
        Long userId = BaseContext.getLoginUserId();
        order.setUserId(userId);

        // 2、查询当前用户的购物车数据
        List<ShoppingCart> shoppingCartList = shoppingCartService.listShoppingCart();
        if (CollectionUtils.isEmpty(shoppingCartList)) {
            throw new TakeOutException("该用户购物车为空!");
        }

        // 3、查询订单地址
        Address address = addressService.getById(order.getAddressId());
        if (ObjectUtils.isEmpty(address)) {
            throw new TakeOutException("该用户地址信息为空!");
        }

        // 设置订单id
        long orderId = IdWorker.getId();
        order.setId(orderId);

        // 4、计算订单总金额、封装订单详情集合
        BigDecimal total = new BigDecimal(0);
        List<OrderDetail> orderDetails = shoppingCartList.stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            // 封装订单详情
            orderDetail.setOrderId(orderId);
            orderDetail.setName(item.getName());
            orderDetail.setDishesId(item.getDishesId());
            orderDetail.setSetMealId(item.getSetMealId());
            orderDetail.setDishesFlavor(item.getDishesFlavor());
            orderDetail.setImageUrl(item.getImageUrl());
            orderDetail.setAmount(item.getAmount());
            orderDetail.setNumber(item.getNumber());

            // 计算总金额
            total.add(item.getAmount().multiply(BigDecimal.valueOf(item.getNumber())));

            return orderDetail;
        }).collect(Collectors.toList());

        order.setAmount(total);

        // 设置订单的其他属性
        order.setOrderTime(new Date());
        order.setAddress(address.getDetailAddress());
        order.setPayTime(new Date());
        order.setPhone(address.getPhone());
        order.setReceiver(address.getReceiver());
        order.setStatus((short) 2);

        // 5、保存订单
        ordersMapper.insert(order);

        // 6、保存订单详细信息
        orderDetailService.saveBatch(orderDetails);

        // 7、清空用户购物车
        shoppingCartService.clear();

    }
}

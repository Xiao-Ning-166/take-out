package com.example.takeout.modules.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.modules.common.dto.OrdersDTO;
import com.example.takeout.modules.order.entity.Orders;
import com.example.takeout.modules.order.service.IOrdersService;
import com.example.takeout.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoning
 * @date 2022/07/28
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {

    @Autowired
    private IOrdersService orderService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<?> list(@RequestParam(name = "current", defaultValue = "1") Integer current,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        IPage<OrdersDTO> page = new Page<>(current, size);
        IPage<OrdersDTO> pageInfo = orderService.listOrders(page);

        return Result.OK(pageInfo);
    }

    /**
     * 查询用户最新订单
     *
     * @return
     */
    @GetMapping("/last")
    @ApiOperation(value = "查询用户最新订单", notes = "查询用户最新订单")
    public Result<?> getLastOrder() {
        OrdersDTO ordersDTO = orderService.getLastOrder();

        return Result.OK(ordersDTO);
    }

    /**
     * 添加一个订单
     *
     * @param order
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加订单", notes = "添加订单")
    public Result<?> add(@RequestBody Orders order) {

        orderService.add(order);

        return Result.OK();
    }

}

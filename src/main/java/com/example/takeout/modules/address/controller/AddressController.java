package com.example.takeout.modules.address.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.takeout.modules.address.entity.Address;
import com.example.takeout.modules.address.service.IAddressService;
import com.example.takeout.utils.BaseContext;
import com.example.takeout.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地址管理
 *
 * @author xiaoning
 * @date 2022/07/21
 */
@Slf4j
@RestController
@RequestMapping("/address")
@Api(tags = "地址管理")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    /**
     * 查询用户收货地址
     *
     * @param request
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    public Result<?> list(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotNull(userId), Address::getUserId, userId)
                .ne(Address::getIsDelete, 1);
        List<Address> list = addressService.list(queryWrapper);

        return Result.OK(list);
    }

    /**
     * 新增一个收货地址
     *
     * @param address
     * @param request
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增收货地址", notes = "新增收货地址")
    public Result<?> add(@RequestBody Address address, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        address.setUserId(userId);
        // 保存地址信息
        addressService.save(address);
        return Result.OK();
    }

    /**
     * 通过id获取收货地址信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    @ApiOperation(value = "查询收货地址", notes = "查询收货地址")
    public Result<?> getById(Long id) {
        Address address = addressService.getById(id);

        return Result.OK(address);
    }

    /**
     * 修改地址信息
     *
     * @param address
     * @return
     */
    @PutMapping("/edit")
    @ApiOperation(value = "修改收货地址", notes = "修改收货地址")
    public Result<?> edit(@RequestBody Address address) {
        address.setUserId(BaseContext.getLoginUserId());
        addressService.updateById(address);

        return Result.OK().success("地址修改成功!");
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除收货地址", notes = "删除收货地址")
    public Result<?> delete(Long id) {
        LambdaUpdateWrapper<Address> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Address::getId, id).set(Address::getIsDelete, 1);
        addressService.update(updateWrapper);

        return Result.OK().success("地址已删除!");
    }

    /**
     * 设置默认收货地址
     *
     * @param id
     * @return
     */
    @PutMapping("/default/{id}")
    @ApiOperation(value = "设置默认收货地址", notes = "设置默认收货地址")
    public Result<?> setDefaultAddress(@PathVariable Long id) {
        Long userId = BaseContext.getLoginUserId();
        addressService.setDefaultAddress(id, userId);

        return Result.OK();
    }

    /**
     * 得到默认收货地址
     *
     * @return
     */
    @GetMapping("/default")
    @ApiOperation(value = "查询默认收货地址", notes = "查询默认收货地址")
    public Result<?> getDefaultAddress() {
        Address address = addressService.getDefaultAddress();

        return Result.OK(address);
    }
}

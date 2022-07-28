package com.example.takeout.modules.address.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.address.entity.Address;

/**
 * @author xiaoning
 * @date 2022/07/21
 */
public interface IAddressService extends IService<Address> {

    /**
     * 设置默认收货地址
     *
     * @param id
     * @param userId
     */
    void setDefaultAddress(Long id, Long userId);

    /**
     * 得到用户默认收货地址
     *
     * @return
     */
    Address getDefaultAddress();
}

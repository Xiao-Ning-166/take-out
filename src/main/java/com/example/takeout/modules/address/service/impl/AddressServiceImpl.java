package com.example.takeout.modules.address.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.address.entity.Address;
import com.example.takeout.modules.address.mapper.AddressMapper;
import com.example.takeout.modules.address.service.IAddressService;
import com.example.takeout.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaoning
 * @date 2022/07/21
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 设置默认收货地址
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefaultAddress(Long id, Long userId) {
        // 1、将该用户下所有的收货地址设置为非默认
        addressMapper.updateAddressStatus(null, userId, 0);
        // 2、设置默认收货地址
        addressMapper.updateAddressStatus(id, userId, 1);
    }

    /**
     * 得到用户默认收货地址
     *
     * @return
     */
    @Override
    public Address getDefaultAddress() {
        Long loginUserId = BaseContext.getLoginUserId();

        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, loginUserId)
                    .eq(Address::getIsDefault, 1);
        Address address = addressMapper.selectOne(queryWrapper);

        return address;
    }
}

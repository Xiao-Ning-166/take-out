package com.example.takeout.modules.address.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeout.modules.address.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/07/21
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 修改地址状态
     *
     * @param id
     * @param userId
     * @param isDefault
     */
    void updateAddressStatus(Long id, Long userId, int isDefault);
}

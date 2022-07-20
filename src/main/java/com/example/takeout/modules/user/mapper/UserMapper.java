package com.example.takeout.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeout.modules.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/07/19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

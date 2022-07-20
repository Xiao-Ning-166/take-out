package com.example.takeout.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.user.entity.User;
import com.example.takeout.modules.user.mapper.UserMapper;
import com.example.takeout.modules.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoning
 * @date 2022/07/19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}

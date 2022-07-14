package com.example.takeout.modules.dishes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.dishes.entity.Flavor;
import com.example.takeout.modules.dishes.mapper.FlavorMapper;
import com.example.takeout.modules.dishes.service.IFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoning
 * @date 2022/07/12
 */
@Service
public class FlavorServiceImpl extends ServiceImpl<FlavorMapper, Flavor> implements IFlavorService {
}

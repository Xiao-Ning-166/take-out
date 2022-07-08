package com.example.takeout.modules.dishes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.dishes.entity.Dishes;
import com.example.takeout.modules.dishes.mapper.DishesMapper;
import com.example.takeout.modules.dishes.service.IDishesService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements IDishesService {
}

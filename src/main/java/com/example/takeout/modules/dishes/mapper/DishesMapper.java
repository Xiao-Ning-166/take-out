package com.example.takeout.modules.dishes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeout.modules.dishes.entity.Dishes;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Mapper
public interface DishesMapper extends BaseMapper<Dishes> {
}

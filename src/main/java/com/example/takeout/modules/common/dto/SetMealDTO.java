package com.example.takeout.modules.common.dto;

import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.entity.SetMealDishes;
import lombok.Data;

import java.util.List;

/**
 * 套餐数据传输对象
 *
 * @author xiaoning
 * @date 2022/07/18
 */
@Data
public class SetMealDTO extends SetMeal {

    /**
     * 套餐菜品集合
     */
    private List<SetMealDishes> setMealDishesList;

    /**
     * 套餐类型名
     */
    private String categoryName;

}

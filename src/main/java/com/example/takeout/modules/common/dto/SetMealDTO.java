package com.example.takeout.modules.common.dto;

import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.entity.SetMealDishes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 套餐数据传输对象
 *
 * @author xiaoning
 * @date 2022/07/18
 */
@Data
@ApiModel(value="套餐传输对象")
public class SetMealDTO extends SetMeal {

    /**
     * 套餐菜品集合
     */
    @ApiModelProperty(value = "套餐菜品集合")
    private List<SetMealDishes> setMealDishesList;

    /**
     * 套餐类型名
     */
    @ApiModelProperty(value = "套餐菜品集合")
    private String categoryName;

}

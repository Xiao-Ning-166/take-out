package com.example.takeout.modules.common.dto;

import com.example.takeout.modules.dishes.entity.Dishes;
import com.example.takeout.modules.dishes.entity.Flavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜品传输对象
 *
 * @author xiaoning
 * @date 2022/07/12
 */
@Data
@ApiModel(value="菜品传输对象")
public class DishesDTO extends Dishes {

    /**
     * 菜品口味集合
     */
    @ApiModelProperty(value = "菜品口味集合")
    private List<Flavor> flavors;

    /**
     * 菜品分类名称
     */
    @ApiModelProperty(value = "菜品分类名称")
    private String categoryName;

}

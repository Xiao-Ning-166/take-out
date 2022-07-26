package com.example.takeout.modules.shoppingCart.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车实体类
 * @author xiaoning
 * @date 2022/07/25
 */
@Data
@TableName("shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 4497579642618103626L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 购物车所属用户的id
     */
    private Long userId;

    /**
     * 如果是菜品，则是对应菜品id
     */
    private Long dishesId;

    /**
     * 如果是套餐，则是对应套餐id
     */
    private Long setMealId;

    /**
     * 菜品/套餐名称
     */
    private String name;

    /**
     * 菜品口味
     */
    private String dishesFlavor;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 菜品/套餐单价
     */
    private BigDecimal amount;

    /**
     * 菜品/套餐数量
     */
    private Integer number;

    /**
     * 创建时间。插入时自动填充
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间。插入和更新时填充字段
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

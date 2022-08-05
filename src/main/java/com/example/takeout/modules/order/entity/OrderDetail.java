package com.example.takeout.modules.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaoning
 * @date 2022/07/27
 */
@Data
@TableName("order_detail")
@ApiModel(value="订单详情对象", description="订单详情表")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -3247792196049053409L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单中，菜品/套餐名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    /**
     * 菜品id
     */
    @ApiModelProperty(value = "菜品id")
    private Long dishesId;

    /**
     * 套餐id
     */
    @ApiModelProperty(value = "套餐id")
    private Long setMealId;

    /**
     * 口味
     */
    @ApiModelProperty(value = "菜品口味")
    private String dishesFlavor;

    /**
     * 菜品/套餐数量
     */
    @ApiModelProperty(value = "商品数量")
    private Integer number;

    /**
     * 金额
     */
    @ApiModelProperty(value = "商品金额")
    private BigDecimal amount;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    /**
     * 创建时间。插入时自动填充
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间。插入和更新时填充字段
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

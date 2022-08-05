package com.example.takeout.modules.setMeal.entity;

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
 * 套餐实体类
 *
 * @author xiaoning
 * @date 2022/07/08
 */
@Data
@TableName("set_meal")
@ApiModel(value="套餐对象", description="套餐表")
public class SetMeal implements Serializable {

    private static final long serialVersionUID = 4888874899423045018L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 套餐分类id
     */
    @ApiModelProperty(value = "套餐分类id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称")
    private String name;

    /**
     * 套餐单价
     */
    @ApiModelProperty(value = "套餐单价")
    private BigDecimal price;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

    /**
     * 套餐描述
     */
    @ApiModelProperty(value = "套餐描述")
    private String description;

    /**
     * 套餐状态。0-停售 1-起售
     */
    @ApiModelProperty(value = "套餐状态 0-停售 1-起售")
    private Short status;

    /**
     * 套餐排序
     */
    @ApiModelProperty(value = "套餐排序")
    private Integer sort;

    /**
     * 是否删除。0-未删除 1-已删除
     */
    @ApiModelProperty(value = "是否删除 0-未删除 1-已删除")
    private Integer isDelete;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;

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

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}

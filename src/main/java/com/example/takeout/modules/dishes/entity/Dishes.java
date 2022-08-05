package com.example.takeout.modules.dishes.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Data
@TableName("dishes")
@ApiModel(value="菜品对象", description="菜品表")
public class Dishes implements Serializable {

    private static final long serialVersionUID = -2762961114126093492L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜品名称
     */
    @ApiModelProperty(value = "菜品名称")
    private String name;

    /**
     * 菜品分类id
     */
    @ApiModelProperty(value = "菜品分类id")
    private Long categoryId;

    /**
     * 菜品价格
     */
    @ApiModelProperty(value = "菜品价格")
    private BigDecimal price;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

    /**
     * 菜品描述
     */
    @ApiModelProperty(value = "菜品描述")
    private String description;

    /**
     * 菜品状态。0-停售 1-起售 2-售罄
     */
    @ApiModelProperty(value = "菜品状态 0-停售 1-起售")
    private Short status;

    /**
     * 菜品顺序
     */
    @ApiModelProperty(value = "菜品顺序")
    private Integer sort;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除 0-未删除 1-已删除")
    private Integer isDelete;

    /**
     * 商品码
     */
    @ApiModelProperty(value = "商品码")
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

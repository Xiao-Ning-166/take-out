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
@TableName("orders")
@ApiModel(value="订单对象", description="订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1065271918569508521L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单状态。1-待付款 2-待派送 3-已派送 4-已完成 5-已取消
     */
    @ApiModelProperty(value = "订单状态 1-待付款 2-待派送 3-已派送 4-已完成 5-已取消")
    private Short status;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "订单所属用户id")
    private Long userId;

    /**
     * 地址id
     */
    @ApiModelProperty(value = "地址id")
    private Long addressId;

    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;

    /**
     * 付款时间
     */
    @ApiModelProperty(value = "付款时间")
    private Date payTime;

    /**
     * 付款方式。1-支付宝 2-微信 3-银联
     */
    @ApiModelProperty(value = "付款方式。1-支付宝 2-微信 3-银联")
    private Short payMethod;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    private BigDecimal amount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话")
    private String phone;

    /**
     * 该订单地址
     */
    @ApiModelProperty(value = "订单地址")
    private String address;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiver;

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

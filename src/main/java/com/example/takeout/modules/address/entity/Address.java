package com.example.takeout.modules.address.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 地址实体类
 *
 * @author xiaoning
 * @date 2022/07/21
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 4268903463689890984L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 性别。1-男 2-女
     */
    private Short sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 标签
     */
    private String tag;

    /**
     * 是否删除。0-未删除 1-已删除
     */
    private Short isDelete;

    /**
     * 是否是默认地址。0-不是 1-是
     */
    private Short isDefault;

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

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}

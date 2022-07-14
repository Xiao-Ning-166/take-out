package com.example.takeout.modules.dishes.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoning
 * @date 2022/07/12
 */
@Data
@TableName("flavor")
public class Flavor implements Serializable {

    private static final long serialVersionUID = -2430698082663362499L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜品id
     */
    private Long dishesId;

    /**
     * 口味名称
     */
    private String name;

    /**
     *
     */
    @TableField(exist = false)
    private Boolean showOption;

    /**
     * 口味数据
     */
    private String value;

    /**
     * 是否删除
     */
    private Integer isDelete;

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

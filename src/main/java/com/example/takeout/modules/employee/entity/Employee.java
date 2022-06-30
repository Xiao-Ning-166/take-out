package com.example.takeout.modules.employee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@TableName("employee")
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = -6890679422869574448L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 状态 0-禁用 1-正常
     */
    private Short status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新人
     */
    private Long updateUser;
}

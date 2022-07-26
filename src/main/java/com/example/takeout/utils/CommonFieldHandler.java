package com.example.takeout.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 公共字段处理。使用MyBatis-plus的自动填充功能
 *
 * @author xiaoning
 * @date 2022/07/07
 */
@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    /**
     * 处理插入时，需要填充的字段
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始处理插入时要填充的字段......");
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        if (metaObject.hasSetter("createUser") && metaObject.hasSetter("updateUser")) {
            metaObject.setValue("createUser", BaseContext.getLoginUserId());
            metaObject.setValue("updateUser", BaseContext.getLoginUserId());
        }
    }

    /**
     * 处理更新时，需要填充的字段
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始处理更新时要填充的字段......");
        metaObject.setValue("updateTime", new Date());
        if (metaObject.hasSetter("updateUser")) {
            metaObject.setValue("updateUser", BaseContext.getLoginUserId());
        }
    }
}

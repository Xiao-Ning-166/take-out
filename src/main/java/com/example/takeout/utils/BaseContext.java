package com.example.takeout.utils;

/**
 * 基于ThreadLocal封装的工具类
 *
 * @author xiaoning
 * @date 2022/07/07
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 当前登录用户id
     *
     * @param id
     */
    public static void setLoginUserId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取登录用户id
     *
     * @return
     */
    public static Long getLoginUserId() {
        return threadLocal.get();
    }

}

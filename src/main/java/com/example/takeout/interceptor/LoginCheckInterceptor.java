package com.example.takeout.interceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.takeout.utils.BaseContext;
import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查拦截器
 * @author xiaoning
 * @date 2022/07/03
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到请求：{}", request.getRequestURI());
        // 1、检查用户登录状态
        // 从session中尝试获取用户
        Long empId = (Long) request.getSession().getAttribute("empId");
        if (ObjectUtils.isNull(empId)) {
            // 说明用户未登录
            log.error("用户未登录！！！");
            response.getWriter().write(JSON.toJSONString(Result.OK().success("NOTLOGIN")));
            return false;
        }
        // 2、说明用户已登录
        // 设置登录用户id到ThreadLocal中
        BaseContext.setLoginUserId(empId);
        return true;
    }
}

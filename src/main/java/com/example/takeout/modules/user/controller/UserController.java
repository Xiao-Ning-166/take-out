package com.example.takeout.modules.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.takeout.modules.user.entity.User;
import com.example.takeout.modules.user.service.IUserService;
import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xiaoning
 * @date 2022/07/19
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        // 1、得到用户输入的验证码、手机号
        String code = map.get("code").toString();
        String phone = map.get("phone").toString();
        // 2、从Redis中得到该用户真正的验证码
        // String realCode = request.getSession().getAttribute("verificationCode").toString();
        String realCode = (String) redisTemplate.opsForValue().get("login::" + phone);

        // 3、判断用户登录验证码
        if (StrUtil.isBlank(code) || StrUtil.isBlank(realCode)) {
            return Result.error("验证码错误!");
        }
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getPhone, phone);
        User user = userService.getOne(query);
        if (ObjectUtils.isNull(user)) {
            // 新用户，创建用户
            user = new User();
            user.setPhone(phone);
            user.setUsername(phone);
            userService.save(user);
        }
        // 4、将user_id保存到session中
        request.getSession().setAttribute("userId", user.getId());
        return Result.OK().success("登录成功!");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result<?> getUser(HttpServletRequest request) {
        // 1、从session中获取user_id
        String userId = request.getSession().getAttribute("userId").toString();
        if (StrUtil.isBlank(userId)) {
            return Result.error("用户未登录!");
        }
        // 2、查询用户
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getId, userId);
        User user = userService.getOne(query);
        if (ObjectUtils.isNull(user)) {
            return Result.error("用户不存在!");
        }
        return Result.OK(user);
    }

}

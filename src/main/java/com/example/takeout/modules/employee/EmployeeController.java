package com.example.takeout.modules.employee;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.takeout.constant.CommonConstant;
import com.example.takeout.modules.employee.entity.Employee;
import com.example.takeout.modules.employee.service.IEmployeeService;
import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 后台登录接口
     * @return
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody Employee employee, HttpServletRequest request) {
        // 1、判断相关参数是否为空
        if (StringUtils.isEmpty(employee.getUsername()) || StringUtils.isEmpty(employee.getPassword())) {
            return Result.error("用户名或密码为空!");
        }
        // 2、通过用户名查找用户，验证用户名
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee empInfo = employeeService.getOne(queryWrapper);
        if (empInfo == null) {
            return Result.error("用户名或密码错误!");
        }
        // 3、验证密码
        // 3.1、将用户提交的密码进行MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        // 3.2、数据库中的密码比较
        if (!md5Password.equals(empInfo.getPassword())) {
            return Result.error("用户名或密码错误!");
        }
        // 4、判断员工状态
        if (CommonConstant.EMPLOYEE_STATUS_DISABLED.equals(empInfo.getStatus())) {
            return Result.error("账号已被禁用，请联系管理员!");
        }
        // 5、将用户信息存储到session中
        request.getSession().setAttribute("empId", empInfo.getId());
        return Result.OK(empInfo).success("登录成功，正在跳转!");
    }


    /**
     * 员工退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        // 1、清楚session中当前登录员工id
        request.getSession().removeAttribute("empId");
        // 2、返回结果
        return Result.OK().success("退出成功!");
    }
}

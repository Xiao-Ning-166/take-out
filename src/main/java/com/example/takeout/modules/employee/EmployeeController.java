package com.example.takeout.modules.employee;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.constant.CommonConstant;
import com.example.takeout.modules.employee.entity.Employee;
import com.example.takeout.modules.employee.service.IEmployeeService;
import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    /**
     * 分页查询员工信息
     * @param employee
     * @param current 当前页码
     * @param size 每页大小
     * @return
     */
    @GetMapping("/list")
    public Result<?> list(Employee employee,
                          @RequestParam(name="current", defaultValue="1") Integer current,
                          @RequestParam(name="size", defaultValue="10") Integer size) {
        IPage<Employee> employeePage = new Page<>(current, size);
        IPage<Employee> employeeList = employeeService.queryList(employee, employeePage);
        return Result.OK(employeeList);
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Employee employee, HttpServletRequest request) {

        Long empId = (Long) request.getSession().getAttribute("empId");
        employeeService.add(employee, empId);

        return Result.OK().success("员工添加成功!");
    }
}

package com.example.takeout.modules.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.exception.TakeOutException;
import com.example.takeout.modules.employee.entity.Employee;
import com.example.takeout.modules.employee.mapper.EmployeeMapper;
import com.example.takeout.modules.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询员工信息
     *
     * @param employee
     * @param page
     * @return
     */
    @Override
    public IPage<Employee> queryList(Employee employee, IPage<Employee> page) {
        return employeeMapper.queryList(employee, page);
    }

    /**
     * 添加员工
     *
     * @param employee 新员工信息
     * @param empId    添加人主键
     */
    @Override
    public void add(Employee employee, Long empId) {
        // 检查用户名是否已存在
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", employee.getUsername());
        List<Employee> employeeList = employeeMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotNull(employeeList)) {
            throw new TakeOutException("用户名已存在!");
        }
        // 设置默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        // 设置时间
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        // 设置创建人
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        // 保存员工信息
        employeeMapper.insert(employee);
    }

    /**
     * 修改员工信息
     *
     * @param employee
     * @param empId
     */
    @Override
    public void edit(Employee employee, Long empId) {
        // 设置更新时间
        employee.setUpdateTime(new Date());
        // 设置更新人
        employee.setUpdateUser(empId);
        // 更新员工信息
        employeeMapper.updateById(employee);
    }
}

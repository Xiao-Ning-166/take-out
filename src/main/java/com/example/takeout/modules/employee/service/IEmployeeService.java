package com.example.takeout.modules.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.employee.entity.Employee;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 添加员工
     * @param employee 新员工信息
     * @param empId 添加人主键
     */
    void add(Employee employee, Long empId);

}

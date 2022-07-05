package com.example.takeout.modules.employee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.employee.entity.Employee;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 分页查询员工信息
     *
     * @param employee
     * @param page
     * @return
     */
    IPage<Employee> queryList(Employee employee, IPage<Employee> page);

    /**
     * 添加员工
     *
     * @param employee 新员工信息
     * @param empId    添加人主键
     */
    void add(Employee employee, Long empId);

    /**
     * 修改员工信息
     *
     * @param employee
     * @param empId
     */
    void edit(Employee employee, Long empId);
}

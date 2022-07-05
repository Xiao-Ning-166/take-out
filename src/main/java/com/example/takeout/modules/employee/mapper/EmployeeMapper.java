package com.example.takeout.modules.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.takeout.modules.employee.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 分页查询员工信息
     *
     * @param employee
     * @param page
     * @return
     */
    IPage<Employee> queryList(Employee employee, IPage<Employee> page);
}

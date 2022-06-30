package com.example.takeout.modules.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeout.modules.employee.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}

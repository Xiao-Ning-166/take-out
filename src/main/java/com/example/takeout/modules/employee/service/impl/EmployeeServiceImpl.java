package com.example.takeout.modules.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.employee.entity.Employee;
import com.example.takeout.modules.employee.mapper.EmployeeMapper;
import com.example.takeout.modules.employee.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
}

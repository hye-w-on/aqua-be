package com.aqua.aquabe.service.admin;

import java.util.List;

import com.aqua.aquabe.model.employee.Employee;

public interface EmployeeService {

    List<Employee> getEmployees();

    Integer createEmployee(Employee employee);

}

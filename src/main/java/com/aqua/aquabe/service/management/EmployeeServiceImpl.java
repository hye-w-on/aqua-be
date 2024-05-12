package com.aqua.aquabe.service.management;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aqua.aquabe.model.employee.Employee;
import com.aqua.aquabe.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Integer createEmployee(Employee employee) {
        Employee e = employeeRepository.save(employee);
        System.out.println(e.toString());
        return 1;
    }

}

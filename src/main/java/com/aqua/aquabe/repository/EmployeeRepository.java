package com.aqua.aquabe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aqua.aquabe.model.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findTopEmployeeByEmployeeId(String employeeId);

}

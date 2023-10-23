package com.aqua.aquabe.model.session;

import com.aqua.aquabe.model.employee.Employee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeSessionVO {

    private Long employeeNo;

    private String employeeId;

    private String languageCode;

    // private List<String> roleCodes;

    public EmployeeSessionVO(Employee employee, String languageCode) {
        this.employeeNo = employee.getEmployeeNo();
        this.employeeId = employee.getEmployeeId();
        this.languageCode = languageCode;
        // this.roleCodes = roleCodes;
    }
}

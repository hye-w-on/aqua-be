package com.aqua.aquabe.model.common;

import java.time.LocalDateTime;

import com.aqua.aquabe.model.session.EmployeeSessionVO;
import com.aqua.aquabe.util.EmployeeSessionUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class DefaultVO {

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String updatedBy;
    private LocalDateTime updatedDatetime;

    public DefaultVO() {
        EmployeeSessionVO employeeSession = EmployeeSessionUtil.getEmployeeSession();
        this.createdBy = employeeSession.getEmployeeId();
        this.createdDatetime = LocalDateTime.now();
        this.updatedBy = employeeSession.getEmployeeId();
        this.updatedDatetime = LocalDateTime.now();
    }

}

package com.aqua.aquabe.model.employee;

import com.aqua.aquabe.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

//@Data
@Getter
@Setter
@Builder // TODO
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_no")
    private Long employeeNo;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    /** Department에 addEmployees가 있으므로 충돌방지를 위해 사용하지 않음 */
    /*
    public void changeDepartment(Department department) {
        this.department = department;
        department.getEmployees().add(this);
    }*/

    @Enumerated(EnumType.STRING)
    @Column(name = "role_code")
    private RoleCode roleCode;

}

package com.aqua.aquabe.model.session;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeLoginRequestVO {

    private Long employeeNo;

    private String employeeId;

    private String LanguageCode;
}

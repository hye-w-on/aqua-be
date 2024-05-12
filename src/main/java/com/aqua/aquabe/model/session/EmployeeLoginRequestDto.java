package com.aqua.aquabe.model.session;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeLoginRequestDto {

    @Schema(description = "사원번호", example = "-1")
    private Long employeeNo;

    @Schema(description = "사원ID", example = "admin")
    private String employeeId;

    @Schema(description = "언어코드", example = "ko")
    private String languageCode;
}

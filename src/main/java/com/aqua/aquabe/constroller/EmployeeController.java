package com.aqua.aquabe.constroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.employee.Employee;
import com.aqua.aquabe.service.management.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employee")
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "직원 조회")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseVO<List<Employee>>> getEmployees() {
        return new ResponseEntity<>(CommonResponseVO.<List<Employee>>builder()
                .successOrNot(YnConstants.Y)
                .statusCode(StatusCodeConstants.SUCCESS)
                .data(employeeService.getEmployees())
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "직원 등록")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseVO<Integer>> createEmployee(
            @RequestBody Employee employee) {
        return new ResponseEntity<>(CommonResponseVO.<Integer>builder()
                .successOrNot(YnConstants.Y)
                .statusCode(StatusCodeConstants.SUCCESS)
                .data(employeeService.createEmployee(employee))
                .build(), HttpStatus.OK);
    }

}

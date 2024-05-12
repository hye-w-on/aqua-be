package com.aqua.aquabe.constroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/health")
@Tag(name = "HealthCheck")
public class HealthController {

    @Operation(summary = "Health Check")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CommonResponseVO<String>> healthCheck() {

        return new ResponseEntity<>(
                CommonResponseVO.<String>builder()
                        .successOrNot(YnConstants.Y)
                        .statusCode(StatusCodeConstants.SUCCESS)
                        .data("Ok")
                        .build(),
                HttpStatus.OK);
    }
}

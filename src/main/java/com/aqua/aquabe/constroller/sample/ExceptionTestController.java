package com.aqua.aquabe.constroller.sample;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.service.sample.ExceptionTestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sample")
@Validated
public class ExceptionTestController {
    private final ExceptionTestService exceptionTestService;

    @Operation(summary = "exception Test", description = "exception Test")
    @GetMapping(value = "/v1/sample/exception/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callException1() throws SQLException, IOException {
        // catch 나 throws 누락시 IDE에서 Syntax Error
        // RuntimeException은 throws를 강제하지않음
        exceptionTestService.callCheckedSQLException();
        exceptionTestService.callCheckedIOException();
        exceptionTestService.callUnCheckedException();
    }

    @Operation(summary = "exception Test", description = "exception Test")
    @GetMapping(value = "/v1/sample/exception/2", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callException2() {
        try {
            // catch 나 throws 누락시 IDE에서 Syntax Error
            exceptionTestService.callCheckedSQLException();
            exceptionTestService.callCheckedIOException();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // RuntimeException은 catch를 강제하지않음
        exceptionTestService.callUnCheckedException();
    }

    @Operation(summary = "Checked exception Rollback Test", description = "Checked exception은 Transaction Rollback 안됨")
    @GetMapping(value = "/v1/sample/rollback/checked-exception", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callCheckedExceptionAndRollback() {

        try {
            exceptionTestService.callCheckedExceptionAndRollbackFail();
            // exceptionTestService.callCheckedExceptionAndRollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Operation(summary = "Unchecked exception Rollback Test", description = "Unchecked exception은 Transaction Rollback 됨")
    @GetMapping(value = "/v1/sample/rollback/unchecked-exception", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callUnCheckedExceptionAndRollback() {

        exceptionTestService.callUnCheckedExceptionAndRollback();
        // exceptionTestService.callUnCheckedExceptionAndRollbackFail();
    }

    @Operation(summary = "예외전환 Rollback Test", description = "Checked exception -> Unchecked exception, 예외전환 후 Rollback 됨")
    @GetMapping(value = "/v1/sample/rollback/exception-translation", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callExceptionTranslationAndRollback() {

        exceptionTestService.callExceptionTranslationAndRollback();

    }

    @Operation(summary = "예외전파 Rollback Test", description = "예외전파 후 Rollback 테스트")
    @GetMapping(value = "/v1/sample/rollback/exception-propagation", produces = MediaType.APPLICATION_JSON_VALUE)
    public void callExceptionPropagationAndRollback() {

        try {
            exceptionTestService.callExceptionPropagationAndRollback1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.aqua.aquabe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseVO<Object>> restApiExceptionHandler(Exception e) {
        log.info(e.getClass().getName(), e);
        log.error(e.getMessage(), e);

        return new ResponseEntity<>(CommonResponseVO.builder()
                .successOrNot(YnConstants.N)
                .statusCode(StatusCodeConstants.INTERNAL_SERVER_ERROR)
                .data(e.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponseVO<Object>> restApiBusinessExceptionHandler(BusinessException e) {
        log.info(e.getClass().getName(), e);
        log.error(e.getMessage(), e);

        return new ResponseEntity<>(CommonResponseVO.builder()
                .successOrNot(YnConstants.N)
                .statusCode(e.getStatusCode())
                .data(e.getMessage())
                .build(), HttpStatus.OK);
    }

    /*
     * @ExceptionHandler({
     * ConstraintViolationException.class,
     * MethodArgumentTypeMismatchException.class,
     * HttpMessageNotReadableException.class
     * })
     * public ResponseEntity<CommonResponseVO<Object>>
     * constraintViolationExceptionHandler(Exception e) {
     * log.error(e.getMessage(), e);
     * 
     * return new ResponseEntity<>(CommonResponseVO.builder()
     * .successOrNot(YnConstants.N)
     * .statusCode(StatusCodeConstants.PARAMETER_VALUE_ERROR)
     * .data(e.getMessage())
     * .build(), HttpStatus.OK);
     * }
     * 
     * @ExceptionHandler({
     * MissingServletRequestParameterException.class,
     * BindException.class
     * })
     * public ResponseEntity<CommonResponseVO<Object>>
     * missingServletRequestParameterExceptionHandler(
     * Exception e) {
     * log.error(e.getMessage(), e);
     * 
     * return new ResponseEntity<>(CommonResponseVO.builder()
     * .successOrNot(YnConstants.N)
     * .statusCode(StatusCodeConstants.MANDATORY_PARAM_ERROR)
     * .build(), HttpStatus.OK);
     * }
     * 
     * @ExceptionHandler(MethodArgumentNotValidException.class)
     * public ResponseEntity<CommonResponseVO<Object>>
     * parameterValidationExceptionHandler(
     * MethodArgumentNotValidException e) {
     * log.error(e.getMessage(), e);
     * 
     * StringBuilder mandatoryParameterStringBuilder = new StringBuilder();
     * List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
     * for (int i = 0; i < fieldErrors.size();) {
     * mandatoryParameterStringBuilder.append(fieldErrors.get(i).getField());
     * 
     * if (++i < fieldErrors.size()) {
     * mandatoryParameterStringBuilder.append(",");
     * }
     * }
     * 
     * return new ResponseEntity<>(CommonResponseVO.builder()
     * .successOrNot(YnConstants.N)
     * .statusCode(StatusCodeConstants.MANDATORY_PARAM_ERROR)
     * .data(mandatoryParameterStringBuilder.toString())
     * .build(), HttpStatus.OK);
     * }
     */
}

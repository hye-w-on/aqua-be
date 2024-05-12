package com.aqua.aquabe.config;

import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.exception.BusinessException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class SampleClientErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BusinessException(StatusCodeConstants.FAIL, "BadRequest");
            case 401:
                return new BusinessException(StatusCodeConstants.FAIL, "Unauthorized");
            case 403:
                return new BusinessException(StatusCodeConstants.FAIL, "Forbidden");
            case 404:
                return new BusinessException(StatusCodeConstants.FAIL, "NotFound");
            default:
                return errorDecoder.decode(methodKey, response); // new Default().decode(methodKey, response);
            // 400대 에러는 FeignClientException
            // 500대 에러는 FeignServerException
        }
    }

}
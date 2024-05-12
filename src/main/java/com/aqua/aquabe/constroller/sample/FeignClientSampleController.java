package com.aqua.aquabe.constroller.sample;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.service.common.SampleFeignClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Feign Client Sample")
@Validated
public class FeignClientSampleController {

    private final SampleFeignClient sampleFeignClient;

    @Operation(summary = "Feign Client Success Sample", description = "Feign Client Sample : get TodoList")
    @GetMapping(value = "/v1/sample/feign/todos/success", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getTodoSuccess() {
        return sampleFeignClient.getTodos(1);
    }

    @Operation(summary = "Feign Client Fail Sample", description = "Feign Client Sample : get TodoList")
    @GetMapping(value = "/v1/sample/feign/todos/fail", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getTodoFail() {
        return sampleFeignClient.getTodos(0);
    }

}

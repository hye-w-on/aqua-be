package com.aqua.aquabe.constroller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.aqua.aquabe.model.sample.ConditionDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

@RestController
@RequiredArgsConstructor
@Tag(name = "model Sample")

public class TestController {

    @Operation(summary = "Test", description = "Test")
    @GetMapping(value = "/v1/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public void Test(@ModelAttribute ConditionDto condition) {
        System.out.println("---result----");
        System.out.println(condition.getTestId());
    }
}

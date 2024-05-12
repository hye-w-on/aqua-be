package com.aqua.aquabe.model.sample;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionDto {
    private String testId;
    private String testName;
}

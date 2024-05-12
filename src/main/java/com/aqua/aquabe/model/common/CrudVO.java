package com.aqua.aquabe.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CrudVO extends DefaultVO {

    @Schema(description = "데이터 상태", example = "C", allowableValues = { "C", "R", "U", "D" })
    private String action;

}

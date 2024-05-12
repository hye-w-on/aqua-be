package com.aqua.aquabe.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DmlResponseVO {

    @Schema(description = "삽입 행 수", example = "1")
    private Integer insertedRows;

    @Schema(description = "수정 행 수", example = "1")
    private Integer updatedRows;

    @Schema(description = "삭제 행 수", example = "1")
    private Integer deletedRows;

}

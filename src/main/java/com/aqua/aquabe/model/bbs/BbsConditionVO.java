package com.aqua.aquabe.model.bbs;

import com.aqua.aquabe.model.common.PaginationRequestVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BbsConditionVO extends PaginationRequestVO {

    @Schema(description = "카테고리 코드", example = "NOTICE")
    // @Enum(enumClass = BbsCategoryCode.class)
    private String categoryCode;

    @Schema(description = "제목", example = "제목")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String contents;

}

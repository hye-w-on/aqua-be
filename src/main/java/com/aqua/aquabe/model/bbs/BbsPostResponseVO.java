package com.aqua.aquabe.model.bbs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class BbsPostResponseVO {

    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "카테고리 코드", example = "NOTICE")
    private String categoryCode;

    @Schema(description = "카테고리 명", example = "NOTICE")
    private String categoryName;

    @Schema(description = "제목", example = "첨부파일이 있는 게시글")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String contents;

    @Schema(description = "첨부파일 유무")
    private String hasAttachment;

    @Schema(description = "작성자 ID")
    private String createdId;

    @Schema(description = "작성자명")
    private String createdUserName;

    @Schema(description = "작성일시")
    private String createDatetime;

    @Schema(description = "조회수")
    private String viewCount;

}
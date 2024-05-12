package com.aqua.aquabe.constroller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.model.bbs.BbsConditionVO;
import com.aqua.aquabe.model.bbs.BbsPostResponseVO;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.common.PaginationResponseVO;
import com.aqua.aquabe.service.management.BbsPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Bbs")
@Validated
public class BbsPostController {
    private final BbsPostService bbsPostService;
    // FE : get, create/new, edit, delete
    // BE : get/read/find/retrieve, create, modify, remove/disable
    // DB : select, insert, update, delete

    @Operation(summary = "게시글 조회")
    @GetMapping(value = "/bbs/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseVO<PaginationResponseVO<BbsPostResponseVO>>> getBbsPosts(
            @ParameterObject @Valid BbsConditionVO bbsCondition) {
        return new ResponseEntity<>(CommonResponseVO.<PaginationResponseVO<BbsPostResponseVO>>builder()
                .successOrNot(YnConstants.Y)
                .statusCode(StatusCodeConstants.SUCCESS)
                .data(bbsPostService.getBbsPosts(bbsCondition))
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "게시글 삭제")
    @PatchMapping(value = "/bbs/post/{postNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseVO<Integer>> modifyDisableBbsPost(@PathVariable @NotBlank String postNo) {
        return new ResponseEntity<>(CommonResponseVO.<Integer>builder()
                .successOrNot(YnConstants.Y)
                .statusCode(StatusCodeConstants.SUCCESS)
                .data(bbsPostService.modifyDisableBbsPost(postNo))
                .build(), HttpStatus.OK);
    }
}

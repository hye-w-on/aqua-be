package com.aqua.aquabe.constroller;

import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.member.MemberProfileVO;
import com.aqua.aquabe.model.session.MemberSessionVO;
import com.aqua.aquabe.service.member.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

        private final MemberService memberService;

        @Operation(summary = "회원가입")
        @PostMapping
        public ResponseEntity<CommonResponseVO<MemberSessionVO>> signUp(@RequestBody MemberProfileVO memberProfile) {

                return new ResponseEntity<>(CommonResponseVO.<MemberSessionVO>builder()
                                .successOrNot(YnConstants.Y)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(memberService.signUp(memberProfile))
                                .build(), HttpStatus.OK);
        }

        @Operation(summary = "회원가입 with Cognito")
        @PostMapping(value = "/cognito")
        public ResponseEntity<CommonResponseVO<Object>> cognitoSignUp(@RequestBody MemberProfileVO memberProfile) {

                memberService.cognitoSignUp(memberProfile);

                return new ResponseEntity<>(CommonResponseVO.builder()
                                .successOrNot(YnConstants.Y)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .build(), HttpStatus.OK);
        }
}
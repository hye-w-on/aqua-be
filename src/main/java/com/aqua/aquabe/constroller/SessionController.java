package com.aqua.aquabe.constroller;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.common.FileVO;

import com.aqua.aquabe.model.session.SocialLoginRequestVO;
import com.aqua.aquabe.service.session.SessionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {

        private final SessionService sessionService;

        @Operation(summary = "소셜 oAuth 로그인", description = "소셜 oAuth 로그인")
        @PostMapping("/social")
        public ResponseEntity<CommonResponseVO<Object>> socialLogin(
                        @RequestBody SocialLoginRequestVO socialLoginRequest)
                        throws InvalidKeyException, NoSuchAlgorithmException {

                return new ResponseEntity<>(sessionService.socialLogin(socialLoginRequest), OK);
        }

        @PostMapping("/file")
        public ResponseEntity<CommonResponseVO<Object>> file(
                        FileVO fileVO) {
                System.out.println("here");
                System.out.println("fileVO = " + fileVO);
                return null;
        }

}
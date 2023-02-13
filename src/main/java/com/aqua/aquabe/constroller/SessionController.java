package com.aqua.aquabe.constroller;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.session.SocialLoginRequestVO;
import com.aqua.aquabe.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/session")
@CrossOrigin(origins = "*")
public class SessionController {

        private final SessionService sessionService;

        @PostMapping("/social")
        public ResponseEntity<CommonResponseVO<Object>> socialLogin(
                        @RequestBody SocialLoginRequestVO socialLoginRequest)
                        throws InvalidKeyException, NoSuchAlgorithmException {

                return new ResponseEntity<>(sessionService.socialLogin(socialLoginRequest), OK);
        }
}
package com.aqua.aquabe.constroller;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.member.MemberProfileVO;
import com.aqua.aquabe.service.MemberService;

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
@RequestMapping("/v1/member")
@CrossOrigin(origins = "*")
public class MemberController {

        private final MemberService memberService;

        @PostMapping
        public ResponseEntity<CommonResponseVO<Object>> signUp(
                        @RequestBody MemberProfileVO memberProfile)
                        throws InvalidKeyException, NoSuchAlgorithmException {

                return new ResponseEntity<>(memberService.signUp(memberProfile), OK);
        }
}
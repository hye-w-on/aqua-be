package com.aqua.aquabe.constroller;

import com.aqua.aquabe.constants.CommonConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.session.EmployeeLoginRequestVO;
import com.aqua.aquabe.model.session.EmployeeSessionVO;
import com.aqua.aquabe.service.SessionService;
import com.aqua.aquabe.util.RequestScopeUtil;
import com.aqua.aquabe.util.SessionScopeUtil;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/http-session")
public class HttpSessionController {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        private final SessionService sessionService;

        @Operation(summary = "HttpSession 로그인", description = "HttpSession을 이용한 Employee 로그인")
        @PostMapping("/employee")
        public ResponseEntity<CommonResponseVO<EmployeeSessionVO>> httpSessionlogin(
                        @RequestBody EmployeeLoginRequestVO employeeLoginRequest,
                        HttpServletRequest request, HttpServletResponse response) {

                // 직원 조회, 유효성 검사 및 세션VO 생성
                EmployeeSessionVO employeeSession = sessionService.createEmployeeSession(employeeLoginRequest);

                if (employeeSession != null) {
                        // 신규 세션 생성
                        HttpSession session = request.getSession();
                        session.setAttribute(CommonConstants.HTTP_SESSION_KEY, employeeSession);

                        return new ResponseEntity<>(CommonResponseVO.<EmployeeSessionVO>builder()
                                        .successOrNot(CommonConstants.YES)
                                        .statusCode(StatusCodeConstants.SUCCESS)
                                        .data(employeeSession)
                                        .build(), OK);
                } else {
                        return new ResponseEntity<>(CommonResponseVO.<EmployeeSessionVO>builder()
                                        .successOrNot(CommonConstants.NO)
                                        .statusCode(StatusCodeConstants.USER_NOT_FOUND)
                                        .build(), UNAUTHORIZED);
                }

        }

        @Operation(summary = "세션 재조회", description = "세션 재조회")
        @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<EmployeeSessionVO>> getSession(HttpSession session) {
                /* Case 2 */
                // @SessionAttribute으로도 가능하지만 ServletRequestBindingException이 발생할 수 있어 지양
                // getSession(@SessionAttribute(CommonConstants.HTTP_SESSION_KEY)
                // EmployeeSessionVO employeeSession){

                EmployeeSessionVO employeeSession = (EmployeeSessionVO) session
                                .getAttribute(CommonConstants.HTTP_SESSION_KEY);

                if (employeeSession != null) {
                        return new ResponseEntity<>(CommonResponseVO.<EmployeeSessionVO>builder()
                                        .successOrNot(CommonConstants.YES)
                                        .statusCode(StatusCodeConstants.SUCCESS)
                                        .data(employeeSession)
                                        .build(), OK);
                } else {
                        return new ResponseEntity<>(CommonResponseVO.<EmployeeSessionVO>builder()
                                        .successOrNot(CommonConstants.NO)
                                        .statusCode(StatusCodeConstants.SESSION_EXPIRE)
                                        .build(), UNAUTHORIZED);
                }
        }

        @Operation(summary = "HttpSession Scope Check", description = "HttpSession Scope Check Test")
        @GetMapping("/scope")
        public void scopeCheck(
                        @SessionAttribute(CommonConstants.HTTP_SESSION_KEY) EmployeeSessionVO employeeSession,
                        HttpSession session,
                        HttpServletRequest request, HttpServletResponse response) {

                log.info("@SessionAttribute: "
                                + employeeSession);
                log.info("HttpSession: "
                                + session.getAttribute(CommonConstants.HTTP_SESSION_KEY));
                log.info("SessionScopeUtil: "
                                + SessionScopeUtil.getAttribute(CommonConstants.HTTP_SESSION_KEY));
                log.info("RequestScopeUtil: "
                                + RequestScopeUtil.getAttribute(CommonConstants.HTTP_SESSION_KEY));

        }

}
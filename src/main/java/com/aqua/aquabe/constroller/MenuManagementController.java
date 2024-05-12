package com.aqua.aquabe.constroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.menu.MenuManagementVO;
import com.aqua.aquabe.service.management.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "menu", description = "메뉴관리")
@Validated
public class MenuManagementController {
        private final MenuService menuService;

        @Operation(summary = "메뉴관리 메뉴리스트 조회")
        @GetMapping(value = "/management/menu", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<List<MenuManagementVO>>> getManagementMenus() {
                return new ResponseEntity<>(CommonResponseVO.<List<MenuManagementVO>>builder()
                                .successOrNot(YnConstants.Y)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(menuService.getManagementMenus())
                                .build(), HttpStatus.OK);
        }

}

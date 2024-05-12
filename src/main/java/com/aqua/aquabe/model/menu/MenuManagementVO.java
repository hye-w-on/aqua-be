package com.aqua.aquabe.model.menu;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@SuperBuilder
public class MenuManagementVO {

    @Schema(description = "메뉴ID", example = "1")
    private String menuNo;

    @Schema(description = "메뉴명", example = "메뉴")
    private String menuName;

    @Schema(description = "메뉴경로", example = "/test")
    private String menuPath;

    @Schema(description = "상위메뉴ID", example = "-1")
    private String parentMenuNo;

    @Schema(description = "메뉴레벨", example = "1")
    private String level;

    @Schema(description = "정렬순서", example = "1")
    private String sortOrder;

    @Schema(description = "하위메뉴 리스트")
    @Builder.Default
    List<MenuManagementVO> subMenus = new ArrayList<>();

    public static List<MenuManagementVO> transformList(List<MenuManagementVO> menuList) {

        Map<String, MenuManagementVO> itemMap = new HashMap<>();
        List<MenuManagementVO> resultList = new ArrayList<>();

        // 모든 아이템을 Map에 추가
        for (MenuManagementVO item : menuList) {
            itemMap.put(item.getMenuNo(), item);
        }

        // 부모 ID를 찾아서 부모에 자식 추가
        for (MenuManagementVO item : menuList) {
            String parentId = item.getParentMenuNo();
            if (parentId.equals("0")) {
                resultList.add(item);
            } else {
                MenuManagementVO parent = itemMap.get(parentId);
                if (parent != null) {
                    parent.getSubMenus().add(item);
                }
            }
        }

        return resultList;
    }

}
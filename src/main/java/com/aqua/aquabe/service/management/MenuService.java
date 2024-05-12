package com.aqua.aquabe.service.management;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.aquabe.model.menu.MenuManagementVO;
import com.aqua.aquabe.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<MenuManagementVO> getManagementMenus() {

        List<MenuManagementVO> menuList = menuRepository.selectMenuList();

        if (menuList == null || menuList.isEmpty()) {
            return null;
        }

        List<MenuManagementVO> resultList = MenuManagementVO.transformList(menuList);
        return resultList;
    }
}

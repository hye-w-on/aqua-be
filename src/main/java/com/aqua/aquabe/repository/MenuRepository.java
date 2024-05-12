package com.aqua.aquabe.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aqua.aquabe.model.menu.MenuManagementVO;

@Mapper
public interface MenuRepository {

    List<MenuManagementVO> selectMenuList();

}

package com.common.system.service.impl;

import com.common.system.entity.RcMenu;
import com.common.system.entity.ZTreeNode;
import com.common.system.service.MenuService;
import com.common.system.service.ZTreeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/7.
 * Time:14:04
 * ProjectName:Common-admin
 */
@Service
public class ZTreeServiceImpl implements ZTreeService {
    @Autowired
    private MenuService menuService;

    @Override
    public List<ZTreeNode> getZTreeNodes() {
        List<RcMenu> list = menuService.getMenu();
        List<ZTreeNode> zTreeNodeList = new ArrayList();
        for (RcMenu menu:list
                ) {
            ZTreeNode node = new ZTreeNode();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setpId(menu.getpId());
            node.setCode(menu.getCode());
            node.setLevel(menu.getLevel());
            zTreeNodeList.add(node);
        }
        return zTreeNodeList;
    }

    @Override
    public String buildZTree(List<ZTreeNode> zTreeNodeList) {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(zTreeNodeList);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
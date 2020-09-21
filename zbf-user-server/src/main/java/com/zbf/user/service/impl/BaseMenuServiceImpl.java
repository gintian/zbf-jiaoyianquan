package com.zbf.user.service.impl;

import com.zbf.user.entity.BaseMenu;
import com.zbf.user.mapper.BaseMenuMapper;
import com.zbf.user.mapper.BaseUserRoleMapper;
import com.zbf.user.service.IBaseMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
@Service
public class BaseMenuServiceImpl extends ServiceImpl<BaseMenuMapper, BaseMenu> implements IBaseMenuService {

    @Autowired
    private BaseMenuMapper baseMenuMapper;

    @Autowired
    private BaseUserRoleMapper baseUserRoleMapper;

    @Override
    public Map<String, Object> getMenuList(Integer id) {
        return null;
    }
}

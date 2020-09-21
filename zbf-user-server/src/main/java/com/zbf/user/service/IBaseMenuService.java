package com.zbf.user.service;

import com.zbf.user.entity.BaseMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
public interface IBaseMenuService extends IService<BaseMenu> {
    Map<String,Object> getMenuList(Integer id);
}

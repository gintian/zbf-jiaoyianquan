package com.zbf.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbf.user.entity.BaseUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
public interface IBaseUserService extends IService<BaseUser> {

    IPage<BaseUser> selectPageVo(Page<BaseUser> page, BaseUser userVo);

    boolean ChangeUserSta(Long id, Integer zt);
}

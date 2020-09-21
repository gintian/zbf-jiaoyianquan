package com.zbf.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbf.user.entity.BaseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
public interface BaseUserMapper extends BaseMapper<BaseUser> {

    IPage<BaseUser> selectPageVo(Page<BaseUser> page, BaseUser userVo);

    boolean ChangeUserSta(Long id, Integer zt);
}

package com.zbf.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbf.user.entity.BaseUser;
import com.zbf.user.mapper.BaseUserMapper;
import com.zbf.user.service.IBaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {

    @Override
    public IPage<BaseUser> selectPageVo(Page<BaseUser> page, BaseUser userVo) {
        return baseMapper.selectPageVo(page,userVo);
    }

    @Override
    public boolean ChangeUserSta(Long id, Integer zt) {
        return baseMapper.ChangeUserSta(id,zt);
    }
}

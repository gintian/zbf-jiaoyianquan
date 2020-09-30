package com.tong.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tong.entity.ZbfExportTaskHeader;
import com.tong.mapper.ZbfExportTaskHeaderMapper;
import com.tong.service.IZbfExportTaskHeaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tongdaowei
 * @since 2020-09-28
 */
@Service
public class ZbfExportTaskHeaderServiceImpl extends ServiceImpl<ZbfExportTaskHeaderMapper, ZbfExportTaskHeader> implements IZbfExportTaskHeaderService {

    @Override
    public IPage<ZbfExportTaskHeader> selectPageVos(Page<ZbfExportTaskHeader> page, ZbfExportTaskHeader headerVo) {
        return baseMapper.selectPageVos(page,headerVo);
    }
}

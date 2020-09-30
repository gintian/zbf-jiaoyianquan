package com.tong.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tong.entity.ZbfExportTaskHeader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tongdaowei
 * @since 2020-09-28
 */
public interface ZbfExportTaskHeaderMapper extends BaseMapper<ZbfExportTaskHeader> {

    IPage<ZbfExportTaskHeader> selectPageVos(Page<ZbfExportTaskHeader> page, ZbfExportTaskHeader headerVo);
}

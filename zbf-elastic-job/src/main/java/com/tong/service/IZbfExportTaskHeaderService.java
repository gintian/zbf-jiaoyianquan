package com.tong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tong.entity.ZbfExportTaskHeader;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tongdaowei
 * @since 2020-09-28
 */
public interface IZbfExportTaskHeaderService extends IService<ZbfExportTaskHeader> {

    IPage<ZbfExportTaskHeader> selectPageVos(Page<ZbfExportTaskHeader> page, ZbfExportTaskHeader headerVo);
}

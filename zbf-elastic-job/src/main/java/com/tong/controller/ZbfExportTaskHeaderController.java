package com.tong.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tong.entity.ZbfExportTaskHeader;
import com.tong.service.IZbfExportTaskHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tongdaowei
 * @since 2020-09-28
 */
@RestController
@RequestMapping("/header")
public class ZbfExportTaskHeaderController {

    @Autowired
    private IZbfExportTaskHeaderService iZbfExportTaskHeaderService;

    @RequestMapping("list")
    public IPage<ZbfExportTaskHeader> list(ZbfExportTaskHeader headerVo, @RequestParam(defaultValue = "1")Integer current, @RequestParam(defaultValue = "4")Integer size){
        Page<ZbfExportTaskHeader> page=new Page<>(current,size);
        IPage<ZbfExportTaskHeader> pageInfo=iZbfExportTaskHeaderService.selectPageVos(page,headerVo);
        return pageInfo;
    }
}

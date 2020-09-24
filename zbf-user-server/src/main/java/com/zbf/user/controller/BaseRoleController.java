package com.zbf.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbf.common.entity.Dats;
import com.zbf.common.entity.ResponseResult;
import com.zbf.common.utils.UID;
import com.zbf.user.entity.BaseRole;
import com.zbf.user.entity.BaseRoleMenu;
import com.zbf.user.entity.BaseUser;
import com.zbf.user.service.IBaseRoleMenuService;
import com.zbf.user.service.IBaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/role")
public class BaseRoleController {

    @Autowired
    private IBaseRoleService iBaseRoleService;

    @Autowired
    private IBaseRoleMenuService iBaseRoleMenuService;


    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/21 0021 上午 7:40
      *@Param [dats]
      *@return com.zbf.common.entity.ResponseResult
      *@miaoshu  查询所有角色
    **/
    @RequestMapping("selallrole")
    private ResponseResult selallrule(Dats dats){
        System.err.println("角色查询的条件是"+dats.toString());
        QueryWrapper<BaseRole> wrapper=new QueryWrapper<>();
        //模糊查询
        if (dats.getRoleName()!=null&&!("").equals(dats.getRoleName())){
            wrapper.like("name",dats.getRoleName());
        }

        Page<BaseRole> pagehelp=new Page<>(dats.getCurrent(),dats.getSize());
        Page<BaseRole> page = iBaseRoleService.page(pagehelp, wrapper);


        ResponseResult responseResult=new ResponseResult();
        responseResult.setResult(page);
        return responseResult;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/23 0023 下午 1:53
      *@Param [baseRole]
      *@return com.zbf.common.entity.ResponseResult
      *@miaoshu  绑定权限
    **/
    @RequestMapping("add")
    public ResponseResult add(@RequestBody BaseRole baseRole){
        ResponseResult responseResult=new ResponseResult();
        boolean remove=iBaseRoleMenuService.remove(new QueryWrapper<BaseRoleMenu>().eq("role_id",baseRole.getId()));

        for(Long mid:baseRole.getMids()){
            remove=iBaseRoleMenuService.save(new BaseRoleMenu(UID.next(),0,baseRole.getId(),mid));
        }
        responseResult.setResult(remove);
        responseResult.setCode(200);
        return responseResult;
    }

}

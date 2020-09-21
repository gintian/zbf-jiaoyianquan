package com.zbf.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbf.common.entity.Dats;
import com.zbf.common.entity.ResponseResult;
import com.zbf.common.exception.AllStatusEnum;
import com.zbf.common.utils.PoiUtil;
import com.zbf.user.entity.BaseRole;
import com.zbf.user.entity.BaseUser;
import com.zbf.user.entity.BaseUserRole;
import com.zbf.user.service.IBaseRoleService;
import com.zbf.user.service.IBaseUserRoleService;
import com.zbf.user.service.IBaseUserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/user")
public class BaseUserController {

    @Autowired
    private IBaseUserService iBaseUserService;

    @Autowired
    private IBaseRoleService iBaseRoleService;

    @Autowired
    private IBaseUserRoleService iBaseUserRoleService;

    @Value("D://pic//")
    private String filePath;

    @Value("http://localhost:${server.port}/")
    private String fileDomain;

    /**
     *@Author tongdaowei
     *@Description //TODO
     *@Date 2020/9/18 0018 下午 2:30
     *@Param [userVo, current, size]
     *@return com.baomidou.mybatisplus.core.metadata.IPage<com.zbf.user.entity.BaseUser>
     *@miaoshu  列表查询
     **/
    @RequestMapping("list")
    public IPage<BaseUser> list(BaseUser userVo, @RequestParam(defaultValue = "1")Integer current, @RequestParam(defaultValue = "4")Integer size){
        Page<BaseUser> page=new Page<>(current,size);
        IPage<BaseUser> pageInfo=iBaseUserService.selectPageVo(page,userVo);
        return pageInfo;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/19 0019 下午 6:44
      *@Param [dats]
      *@return com.zbf.common.entity.ResponseResult
      *@miaoshu ；列表查询
    **/
    @RequestMapping("listUser")
    public ResponseResult userlist(Dats dats){

        QueryWrapper<BaseUser> queryWrapper=new QueryWrapper<>();
        //模糊查询
        if (dats.getUserName()!=null&&!("").equals(dats.getUserName())){
            queryWrapper.like("userName",dats.getUserName());
        }
        if (dats.getLoginName()!=null&&!("").equals(dats.getLoginName())){
            queryWrapper.like("loginName",dats.getLoginName());
        }if (dats.getTel()!=null&&!("").equals(dats.getTel())){
            queryWrapper.like("tel",dats.getTel());
        }

        Page<BaseUser> pagehelp=new Page<>(dats.getCurrent(),dats.getSize());
        Page<BaseUser> page = iBaseUserService.page(pagehelp, queryWrapper);
        dats.setTotals((int) page.getTotal());
        page.getRecords().forEach(x->{
            x.setBaseRoles(
                    iBaseRoleService.list(new QueryWrapper<BaseRole>().inSql("id","SELECT roleId FROM base_user_role WHERE base_user_role.userId="+x.getId()))
            );

        });

        dats.setDat(page.getRecords());

        ResponseResult responseResult = new ResponseResult();
        responseResult.setResult(dats);
        return responseResult;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/19 0019 下午 6:45
      *@miaoshu   添加权限
    **/

    @RequestMapping("bindRoleForUser")
    public ResponseResult upduser(Dats dats){
        ResponseResult responseResult=new ResponseResult();
        System.err.println("添加权限的数据是"+dats);
        boolean roleId = iBaseUserRoleService.remove(new QueryWrapper<BaseUserRole>().eq("userId", dats.getId()));
        for (Integer id:dats.getIds()){
            BaseUserRole baseUserRole =new BaseUserRole();
            baseUserRole.setRoleId(Long.valueOf(id));
            baseUserRole.setUserId(dats.getId());

            boolean save = iBaseUserRoleService.save(baseUserRole);

        }
        responseResult.setResult(roleId);

        return responseResult;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/19 0019 下午 6:45
      *@miaoshu  处理时间
    **/
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    private LocalDateTime dateToLocalDateTime(Date time) {
        Instant it=time.toInstant();
        ZoneId zid=ZoneId.systemDefault();
        return LocalDateTime.ofInstant(it,zid);
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/19 0019 下午 6:46
      *@Param [baseUser]
      *@return boolean
      *@miaoshu  添加
    **/
    @RequestMapping("add")
    public boolean add(@RequestBody BaseUser baseUser){
        boolean save=iBaseUserService.saveOrUpdate(baseUser);
        return save;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/19 0019 下午 6:47
      *@Param [file]
      *@return java.util.Map<java.lang.String,java.lang.Object>
      *@miaoshu 图片上传
    **/
    @RequestMapping("upload")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file){
        Map<String,Object> map=new HashMap<>();

        String originalFilename = file.getOriginalFilename();

        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID() + extName;

        File file1 = new File(filePath,fileName);

        try {
            file.transferTo(file1);
            map.put("imgUrl",fileDomain+fileName);
            map.put("fileName",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/20 0020 下午 6:44
      *@Param [id, sta]
      *@return com.zbf.common.entity.ResponseResult
      *@miaoshu   改变用户状态
    **/
    @RequestMapping("/ChangeUserZt")
    public ResponseResult ChangeUserZt(Long id,Integer zt){
        System.out.println(id+"操作用户状态"+zt);
        if(zt==1){
            zt=0;
        }else{
            zt=1;
        }
        ResponseResult responseResult=new ResponseResult();
        boolean b=iBaseUserService.ChangeUserSta(id,zt);
        if(b){
            System.out.println(11111);
            responseResult.setCode(AllStatusEnum.REQUEST_SUCCESS.getCode());
            responseResult.setSuccess(AllStatusEnum.REQUEST_SUCCESS.getMsg());
            responseResult.setResult(b);
            System.out.println(22222);
        }else {
            System.out.println(3333);
            responseResult.setCode(AllStatusEnum.REQUEST_SUCCESS.getCode());
            responseResult.setSuccess(AllStatusEnum.REQUEST_SUCCESS.getMsg());
            responseResult.setResult(b);
            System.out.println(4444);
        }
        System.err.println(responseResult.toString());
        return responseResult;
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/20 0020 下午 7:25
      *@miaoshu  导出excel
    **/
    @RequestMapping("toExcel")
    public ResponseResult toExcel(@RequestBody Dats dats){
        System.err.println(dats);
        ResponseResult responseResult = ResponseResult.getResponseResult();
        try {
            //通过数据源获取与数据库的连接  没配置spring的可用原生的jdbc来获取连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shixun?serverTimezone=UTC", "root", "root");
            //查询的sql语句

            //拼接需要查询的字段
            StringBuffer stringBufferSelect = new StringBuffer();
            String types = "";
            if(dats.getType()!=null && dats.getType().length>0){
                types = "";
                for (String type:dats.getType()) {
                    types += type+",";
                }
                stringBufferSelect.append(types.substring(0,types.length()-1));
            }else{
                stringBufferSelect.append("*");
            }

            //拼接需要根据什么排序
            StringBuffer stringBufferOrderType = new StringBuffer();
            String orders = "";
            if(dats.getOrderType()!=null && dats.getOrderType().length>0){
                stringBufferOrderType.append("ORDER BY ");
                orders = "";
                if(dats.getOrderType().length>1){
                    for (String order:dats.getOrderType()) {
                        orders += order+" and ";
                    }
                    stringBufferOrderType.append(orders.substring(0,orders.length()-4));
                }else{
                    String[] orderType = dats.getOrderType();
                    stringBufferOrderType.append(orderType[0]);
                }
            }else{
                stringBufferOrderType.append(" ");
            }

            //拼接需要根据什么排序
            StringBuffer stringBufferOrder = new StringBuffer();
            //拼接需要升序还是降序
            if(dats.getOrder()!=null && !("").equals(dats.getOrder())){
                stringBufferOrder.append(" "+dats.getOrder());
            }else{
                stringBufferOrder.append(" ");
            }

            //拼接要导出多少条数据
            StringBuffer stringBufferLimit = new StringBuffer();
            if(dats.getTotal()!=null && dats.getTotal()!=0){
                stringBufferLimit.append(" LIMIT 0,"+dats.getTotal());
            }else{
                stringBufferOrder.append(" ");
            }

            StringBuilder sql=new StringBuilder("select "+stringBufferSelect+" from base_user u LEFT JOIN base_user_role ur on u.id = ur.userId" +
                    " LEFT JOIN base_role r on r.id = ur.roleId GROUP BY u.id "+stringBufferOrderType+stringBufferOrder+stringBufferLimit);

            System.err.println("打印sql=="+sql);
            //生成的excel表的路径   注意文件名要和数据库中表的名称一致  因为导入时要用到。
            String filePath="D:\\1801D\\"+dats.getExcleName()+".xls";
            //导出
            PoiUtil.exportToExcel(connection,sql.toString(), "base_user", filePath);
            responseResult.setCode(200);
            responseResult.setSuccess("导出excel成功");
            return responseResult;
        }catch (Exception x){
            x.printStackTrace();
            responseResult.setCode(500);
            responseResult.setSuccess("导出excel失败");
            return responseResult;
        }

    }

    /* *
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/20 0020 下午 8:42
      *@Param [baseUser]
      *@return com.zbf.common.entity.ResponseResult
      *@miaoshu  导出word
    **/
    @RequestMapping("toWord")
    public ResponseResult getDoc(@RequestBody BaseUser baseUser) throws IOException, TemplateException {

        List<BaseRole> baseRoleList = baseUser.getBaseRoles();
        if(baseRoleList!=null){
            String s = "";
            for (BaseRole baseRole : baseRoleList) {
                s += baseRole.getName()+",";
            }
            baseUser.setRoleName(s.substring(0,s.length()-1));
        }

        ResponseResult responseResult = new ResponseResult();
        System.out.println("******baseUser*******"+baseUser);
        try {
            Map<String,String> dataMap = new HashMap<String,String>();
            if(baseUser.getId() != null ){
                dataMap.put("id", String.valueOf(baseUser.getId()));
            }else{
                dataMap.put("id", "该用户没有id");
            }
            if(baseUser.getUserName() != null){
                dataMap.put("userName", baseUser.getUserName());
            }else{
                dataMap.put("userName","该用户没有姓名");
            }
            if(baseUser.getLoginName() != null){
                dataMap.put("loginName", baseUser.getLoginName());
            }else{
                dataMap.put("loginName","该用户没有登录姓名");
            }
            if(baseUser.getTel() != null ){
                dataMap.put("tel", baseUser.getTel());
            }else{
                dataMap.put("tel","该用户没有手机号");
            }
            if(baseUser.getRoleName() != null){
                dataMap.put("rolename", baseUser.getRoleName());
            }else{
                dataMap.put("rolename","该用户没有所属角色");
            }

            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式
            configuration.setDirectoryForTemplateLoading(new File("D:\\1801D\\doc"));

            // 输出文档路径及名称
            File outFile = new File("D:\\1801D\\doc/"+baseUser.getUserName()+".doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("word.xml","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();

            responseResult.setCode(200);
            return responseResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        responseResult.setCode(400);
        return responseResult;

    }


}

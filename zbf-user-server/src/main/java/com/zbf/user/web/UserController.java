package com.zbf.user.web;

import com.alibaba.fastjson.JSONObject;
import com.zbf.common.entity.ResponseResult;
import com.zbf.common.entity.User;
import com.zbf.common.exception.AllRedisKeyEnum;
import com.zbf.common.utils.*;
import com.zbf.user.dao.UserDao;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
  *@Author tongdaowei
  *@Description //TODO 
  *@Date 2020/9/16 0016 上午 9:00 
  *@Param
  *@return 
  *@miaoshu       
**/  
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserDao userDao;
    /**
     * 描述: 账户的激活地址
     */
    @Value("${active.path}")
    private String activePath;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/toRegister")
    public ResponseResult toRegister(@RequestBody User user) {
        //System.err.println(user+"\\\\\\\\");
        MailQQUtils.sendMessage(user.getEmail(),"","岩蛇注册",getActivePath(activePath,1*60*1000L,user.getLoginName()));

        Map<String, User> userByUserloginName = userDao.getUserByUserloginName(user.getLoginName());
        ResponseResult responseResult = new ResponseResult();
        if (userByUserloginName!=null ){
            responseResult.setCode(0);
            responseResult.setError("登录名已被人使用");
            return responseResult;
        }
        Map<String, User> userByUsermail = userDao.getUserByUsermail(user.getEmail());

        if (userByUsermail!=null ){
            responseResult.setCode(1);
            responseResult.setError("该邮箱已经注册过了");
            return responseResult;
        }
        Map<String, User> userByUserTel = userDao.getUserByUserTel(user.getTel());
        if (userByUserTel!=null ){
            responseResult.setCode(2);
            responseResult.setError("该手机号已经注册过了");
            return responseResult;
        }
        System.out.println("tainjia");
        Boolean save = userDao.save(user);
        if (save){
            responseResult.setCode(6);
            responseResult.setSuccess("注册成功");
            return responseResult;
        }else{
            return responseResult;
        }


    }

    /**
     * 忘记密码的方法
     *
     * @param user
     * @return
     */
    @RequestMapping("upPas")
    public boolean upPas(@RequestBody User user) {
        if (user.getTel().contains("@")){
            return userDao.updatePass(user);
        }
        return userDao.updatePas(user);
    }

    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/16 0016 上午 9:05
      *@Param [request, response]
      *@return void
      *@miaoshu 激活账户
    **/
    @RequestMapping("activeUser")
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取激活的串
        String actived = request.getParameter("actived");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        //设置响应头的格式
        response.setContentType("text/html;charset=UTF-8");
        //解析激活串
        try{
            JSONObject jsonObject = JwtUtilsForOther.decodeJwtTocken(actived);

            JSONObject info = JSON.parseObject(jsonObject.getString("info"));

            //获取存储的激活码
            String code = redisTemplate.opsForValue().get(AllRedisKeyEnum.ACTIVIVE_KEY.getKey() + "_" + info.get("loginName"));

            //激活成功后跳转到激活成功页面
            //在激活成功的页面可以跳转到登录界面，进行登录
            //如果激活码正确
            if(info.get("code").equals(code)){

                stringObjectHashMap.put("loginPath","http://localhost:8080/");
                FreemarkerUtils.getStaticHtml(RestController.class,"/template/","activeOk.html",stringObjectHashMap,response.getWriter());
            }else{
                FreemarkerUtils.getStaticHtml(RestController.class,"/template/","activeError.html",stringObjectHashMap,response.getWriter());
            }

        }catch (ExpiredJwtException e){
            HashMap<String, Object> newData = new HashMap<>();
            String loginName = request.getParameter("loginName");
            newData.put("newActiveLink","http://localhost:10000/user/getNewActiveLink?loginName="+loginName);
            FreemarkerUtils.getStaticHtml(RestController.class,"/template/","activeError.html",newData,response.getWriter());

        }

    }


    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/16 0016 上午 9:05
      *@Param [baseActivePath, timeOut, loginName]
      *@return java.lang.String
      *@miaoshu  这是一个普通的方法用来生成激活链接
      *参数：baseActivePath 激活的基本路劲，激活信息,timeOut 有效期
    **/
    public String getActivePath(String baseActivePath,long timeOut,String loginName){
        //激活信息
        String code= UID.getUUID16();
        //放入redis 中
        String key= AllRedisKeyEnum.ACTIVIVE_KEY.getKey()+"_"+loginName;
        redisTemplate.opsForValue().set(key,code);
        //设置redis的key过期时间
        redisTemplate.expire(key,timeOut, TimeUnit.MILLISECONDS);
        //生成激活的链接地址
        StringBuffer stringBuffer=new StringBuffer(activePath);
        stringBuffer.append("?");
        stringBuffer.append("loginName="+loginName);
        stringBuffer.append("&");
        stringBuffer.append("actived=");
        Map<String,String> map=new HashMap<>();
        map.put("loginName",loginName);
        map.put("code",code);
        stringBuffer.append(JwtUtilsForOther.generateToken(JSON.toJSONString(map),timeOut));
        String path=stringBuffer.toString();
        stringBuffer=null;
        return path;
    }


    /**
      *@Author tongdaowei
      *@Description //TODO
      *@Date 2020/9/16 0016 上午 9:06
      *@Param [request, response]
      *@return void
      *@miaoshu 激活失败重新获取激活链接邮件
    **/
    @RequestMapping("getNewActiveLink")
    public void getNewActiveLink(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        //设置响应头的格式
        response.setContentType("text/html;charset=UTF-8");

        //如果jwt过期，重新的发激活邮件
        String loginName = request.getParameter("loginName");
        //根据loginName获取用户信息

        //3、扣扣邮箱发送激活邮件
        MailQQUtils.sendMessage("2667648594@qq.com","1234","岩蛇网络",getActivePath(activePath,1*60*1000L,loginName));

        FreemarkerUtils.getStaticHtml(RestController.class,"/template/","sendOK.html",stringObjectHashMap,response.getWriter());
    }

}

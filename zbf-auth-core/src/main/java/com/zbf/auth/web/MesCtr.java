package com.zbf.auth.web;




import com.zbf.auth.mapper.UserDao;
import com.zbf.common.entity.Dats;
import com.zbf.common.entity.RandomUtil;
import com.zbf.common.entity.ResponseResult;

import com.zbf.common.utils.MailQQUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author tongdaowei
 * @PACKAGENAME:com.cx.controller
 * @ClassName: MesCtr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/1015:50
 */
@RestController

public class MesCtr {
    @Resource
    UserDao userDao;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 登陆验证码发送
     * @param tel
     * @return
     */
    @RequestMapping("sendmessage")
    public String send(String tel){
        int[] randomNum = RandomUtil.getRandomNum(4);
        String nub="";
       for (int i=0;i<randomNum.length;i++){
           nub+=randomNum[i];
       }
//        RestTest.testSendSms("1378fbc94dfc4924feac4265b065a394","70342cf35970f29f02be3c969b8bcb1d","9f89a5f9603f4bf4a88cb70730655726"
//                ,"565571",nub,tel,"5");
        return nub;
    }

    /**
     *      * 注册短信验证
     *      * @param tel
     * @return
     */
    @RequestMapping("loginsendmessage")
    public ResponseResult loginsendmassage(String tel){
        ResponseResult responseResult=new ResponseResult();
        Map<String, Object> userByTel = userDao.getUserByTel(tel);
        if (userByTel!=null){
            responseResult.setMsg("该电话号码已经注册，请换一个重试");
            responseResult.setCode(500);
        }
        else {
            int[] randomNum = RandomUtil.getRandomNum(4);
        String nub="";
        for (int i=0;i<randomNum.length;i++){
            nub+=randomNum[i];
        }
//        RestTest.testSendSms("1378fbc94dfc4924feac4265b065a394","70342cf35970f29f02be3c969b8bcb1d","9f89a5f9603f4bf4a88cb70730655726"
//                ,"565571",nub,tel,"5");
            responseResult.setCode(200);
            responseResult.setResult(nub);
        }
        return responseResult;
    }

    /**
     * 找回密码
     * @param tel
     * @return
     */
    @RequestMapping("regismessage")
    public ResponseResult regismassage(String tel){
        ResponseResult responseResult=new ResponseResult();


        if(isPhone(tel)){
            Map<String, Object> userByTel = userDao.getUserByTel(tel);
            if (userByTel==null){
                responseResult.setMsg("该电话号码未注册，请换一个重试");
                responseResult.setCode(500);
            }
            else {
                int[] randomNum = RandomUtil.getRandomNum(4);
                String nub="";
                for (int i=0;i<randomNum.length;i++){
                    nub+=randomNum[i];
                }
//        RestTest.testSendSms("1378fbc94dfc4924feac4265b065a394","70342cf35970f29f02be3c969b8bcb1d","9f89a5f9603f4bf4a88cb70730655726"
//                ,"565571",nub,tel,"5");
                responseResult.setCode(200);
                responseResult.setResult(nub);
            }
        }else if (isEmail(tel)){
            Map<String, Object> userByEmail = userDao.getUserByEmail(tel);
            if (userByEmail==null){
                responseResult.setMsg("该邮箱号码未注册，请换一个重试");
                responseResult.setCode(500);
            }
            else {
                int[] randomNum = RandomUtil.getRandomNum(4);
                String nub="";
                for (int i=0;i<randomNum.length;i++){
                    nub+=randomNum[i];
                }
              //  MailQQUtils.sendMessage(tel,"7748","[众生]","您正在进行找回密码操作，您的验证码是"+nub+"，任何向您索要验证码的都是骗子请勿上当受骗！！！");

                responseResult.setCode(200);
                responseResult.setResult(nub);
            }

        }else {
            responseResult.setMsg("请输入正确的邮箱或者电话");
            responseResult.setCode(500);
        }
        return responseResult;
    }

    /**
     *发送邮件
     * @param url
     * @return
     */
    @RequestMapping("sendemail")
    public  String sendemail(String url){
        MailQQUtils.sendMessage(url,"7748","zhangsna","http://localhost:8080/");
        return  "cg";
    }

    /**
     * 修改
     * @param dats
     * @return
     */
    @RequestMapping("updmail")
    public ResponseResult updpwd(Dats dats){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        ResponseResult responseResult=new ResponseResult();
        if (isEmail(dats.getTel())){
            String encode = bCryptPasswordEncoder.encode(dats.getPassWord());
            int i = userDao.updByEmail(dats.getTel(), encode);
            responseResult.setCode(200);
        }else if (isPhone(dats.getTel())){
            String encode = bCryptPasswordEncoder.encode(dats.getPassWord());
            int i = userDao.updByTel(dats.getTel(), encode);
            responseResult.setCode(200);
        }else {
            responseResult.setCode(500);
        }


        return  responseResult;
    }

    @RequestMapping("havaphone")
    public boolean phone(String tel){
        return  havephone(tel);
    };
    @RequestMapping("havaemail")
    public boolean mail(String email){
        return  haveemail(email);
    };
    @RequestMapping("havalogname")
    public boolean login(String name){
        return haveuser(name);
    }














    /**
     * 测试
     * @param cs
     * @param url
     */
    @RequestMapping("tes")
    public  void  sendsss(Integer cs,String url){

      for (int i=0;i<cs;i++){
          MailQQUtils.sendMessage(url,"7748","zhangsna","sb");

      }


    }




    public  boolean havephone(String tel){
        if (userDao.getUserByTel(tel)==null){
            return false;
        }
        return true;
    };


    public boolean haveemail(String email){
        if (userDao.getUserByEmail(email)==null){
            return false;
        }
        return true;
    }

    public boolean haveuser(String username){
        if (userDao.getUserByUserName(username)==null){
                return false;
        }
        return true;
    }

    /**
     * 判断电话
     */
    private static final Pattern PATTERN_PHONE = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    public boolean isPhone(String phone){
        return PATTERN_PHONE.matcher(phone).matches();
    }

    /**
     * 判断邮箱
     */
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    public boolean isEmail(String email){
        return PATTERN_EMAIL.matcher(email).matches();
    }


}

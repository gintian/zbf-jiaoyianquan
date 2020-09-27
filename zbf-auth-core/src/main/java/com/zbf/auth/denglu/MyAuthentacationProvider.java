package com.zbf.auth.denglu;

import com.alibaba.fastjson.JSON;
import com.zbf.auth.mapper.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 9:31
  *@Param
  *@return
  *@miaoshu 这个类主要是用用来认证 密码的  有验证码什么的也可在此认证
**/

@Component
public class MyAuthentacationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserServiceDetail myUserServiceDetail;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MenuDao menuDao;

    private static final Pattern PATTERN_PHONE = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //获取表单的用户名
        String userName = authentication.getPrincipal().toString();
        //获取用户填写的密码
        String password = authentication.getCredentials().toString();
        String re="";
        //判断邮箱
        if(userName.contains("@")){
            String s = redisTemplate.opsForValue().get("code");
            System.out.println("============获取的验证码是:"+s);

            if(s.equals(password)){
                System.out.println("开始进行判断");
                //根据用户名获取用户的信息，这里调用根据用户名获取用户信息的UserServiceDetail类
                UserDetails userDetails = myUserServiceDetail.loadUserByUsername(userName);
                re=userDetails.getUsername();
                //根据获取的用户信息获取用户的密码
                String password1 = userDetails.getPassword();
                //查询所有的权限是不是在Redis，如果不在Redis的话，从数据库查询然后更新到Redis
                if(!redisTemplate.hasKey("menuRole")){
                    List<Map<String, String>> allMenus = menuDao.getAllMenus();
                    Map<String,String> urlRole=new HashMap<>();
                    allMenus.forEach(mapp->{
                        urlRole.put(mapp.get("urlRoleCode"),"");
                    });
                    //将所有的权限存入Redis
                    redisTemplate.opsForHash().putAll("menuRole",urlRole);
                    redisTemplate.expire("menuRole",60*60,TimeUnit.SECONDS);
                }
                //说明：
                //这里一步--》密码校验成功后可以加载一下 当前用户的权限角色 放入缓存中方便鉴权的时候使用
                //userDetails.getAuthorities();//这里存储的是当前用户的角色信息
                //缓存用户的权限信息
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password1, userDetails.getAuthorities());
                String string = JSON.toJSONString(usernamePasswordAuthenticationToken);
                //将当前用户的角色信息 放入缓存大key user-auth 小key用户登录名获取权限，value用户的角色列表
                redisTemplate.opsForHash().put("user-auth",userDetails.getUsername(),string);
                System.out.println("登录成功");
                return usernamePasswordAuthenticationToken;
            }else {
                throw new BadCredentialsException("验证码过期或错误");
            }
        }

        // 判断手机号
        if(isPhone(userName)){
            System.out.println("@@@@@@@@@@@@@@@@"+userName);
            String s = redisTemplate.opsForValue().get("code");
            System.out.println("-----------------验证码:"+s);

            if(s.equals(password)){
                //根据用户名获取用户的信息，这里调用根据用户名获取用户信息的UserServiceDetail类
                UserDetails userDetails = myUserServiceDetail.loadUserByUsername(userName);
                re=userDetails.getUsername();
                //根据获取的用户信息获取用户的密码
                String password1 = userDetails.getPassword();
                //查询所有的权限是不是在Redis，如果不在Redis的话，从数据库查询然后更新到Redis
                if(!redisTemplate.hasKey("menuRole")){
                    List<Map<String, String>> allMenus = menuDao.getAllMenus();
                    Map<String,String> urlRole=new HashMap<>();
                    allMenus.forEach(mapp->{
                        urlRole.put(mapp.get("urlRoleCode"),"");
                    });
                    //将所有的权限存入Redis
                    redisTemplate.opsForHash().putAll("menuRole",urlRole);
                    redisTemplate.expire("menuRole",60*60,TimeUnit.SECONDS);
                }
                //说明：
                //这里一步--》密码校验成功后可以加载一下 当前用户的权限角色 放入缓存中方便鉴权的时候使用
                //userDetails.getAuthorities();//这里存储的是当前用户的角色信息
                //缓存用户的权限信息
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password1, userDetails.getAuthorities());
                String string = JSON.toJSONString(usernamePasswordAuthenticationToken);
                //将当前用户的角色信息 放入缓存大key user-auth 小key用户登录名获取权限，value用户的角色列表
                redisTemplate.opsForHash().put("user-auth",userDetails.getUsername(),string);
                return usernamePasswordAuthenticationToken;
            }else {
                throw new BadCredentialsException("验证码过期或错误");
            }
        }else{
            //加密密码
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            //String encode = bCryptPasswordEncoder.encode(password);
            //根据用户名获取用户的信息，这里调用根据用户名获取用户信息的UserServiceDetail类
            UserDetails userDetails = myUserServiceDetail.loadUserByUsername(userName);
            re=userDetails.getUsername();
            //根据获取的用户信息获取用户的密码
            String password1 = userDetails.getPassword();
            //bCryptPasswordEncoder这个类加密的密码每次是不一样的，所以要比较原始密码的话
            //只能使用bCryptPasswordEncoder.matches方法进行比较
            //这里就是认证了 ^_^ !
            if(password.length()>5){
                if (!bCryptPasswordEncoder.matches(password,password1)){
                    throw new BadCredentialsException("用户名或密码不正确");
                }
            }

            //查询所有的权限是不是在Redis，如果不在Redis的话，从数据库查询然后更新到Redis
            if(!redisTemplate.hasKey("menuRole")){
                List<Map<String, String>> allMenus = menuDao.getAllMenus();
                Map<String,String> urlRole=new HashMap<>();
                allMenus.forEach(mapp->{
                    urlRole.put(mapp.get("urlRoleCode"),"");
                });
                //将所有的权限存入Redis
                redisTemplate.opsForHash().putAll("menuRole",urlRole);
                redisTemplate.expire("menuRole",60*60,TimeUnit.SECONDS);
            }
            //说明：
            //这里一步--》密码校验成功后可以加载一下 当前用户的权限角色 放入缓存中方便鉴权的时候使用
            //userDetails.getAuthorities();//这里存储的是当前用户的角色信息
            //缓存用户的权限信息
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(re, password1, userDetails.getAuthorities());
            String string = JSON.toJSONString(usernamePasswordAuthenticationToken);
            //将当前用户的角色信息 放入缓存大key user-auth 小key用户登录名authentication.getPrincipal()，value用户的角色列表
            redisTemplate.opsForHash().put("user-auth",authentication.getPrincipal().toString(),string);
            return usernamePasswordAuthenticationToken;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public boolean isPhone(String phone){
        return PATTERN_PHONE.matcher(phone).matches();
    }

}

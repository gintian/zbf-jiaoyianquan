package com.zbf.user.dao;

import com.zbf.common.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
  *@Author tongdaowei
  *@Description //TODO 
  *@Date 2020/9/16 0016 上午 9:00 
  *@Param 
  *@return 
  *@miaoshu       
**/  
@Mapper
public interface UserDao {

    @Insert("insert into base_user(userName,loginName,sex,tel,email,passWord) values(#{userName},#{loginName},#{sex},#{tel},#{email},#{passWord})")
    public Boolean save(User user);

    //自定义查询手机号
    @Select("select * from base_user where tel=#{tel}")
    public Map<String, User> getUserByUserTel(String tel);

    //自定义查询邮箱
    @Select("select * from base_user where email=#{email}")
    public Map<String, User> getUserByUsermail(String email);

    //自定义查询登录名
    @Select("select * from base_user where loginName=#{loginName}")
    public Map<String, User> getUserByUserloginName(String loginName);

    //根据手机号修改密码
    @Update("update base_user set passWord=#{passWord} where tel=#{tel}")
    public Boolean updatePas(User user);

    //根据邮箱修改密码
    @Update("update base_user set passWord=#{passWord} where mail=#{tel}")
    public Boolean updatePass(User user);

}

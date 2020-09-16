package com.zbf.auth.mapper;

import com.zbf.common.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/* *
  *@Author tongdaowei
  *@Description //TODO 
  *@Date 2020/9/16 0016 上午 9:01 
  *@Param
  *@return 
  *@miaoshu       
**/    
@Mapper
public interface UserDao {

    @Select("select * from base_user where loginName=#{userName}")
    public Map<String, Object> getUserByUserName(String userName);


/* *
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 8:57
  *@Param [userId]
  *@return java.util.List<java.lang.String>
**/  
    @Select("select UPPER(br.role_code) roleCode from base_role br INNER JOIN base_user_role bur ON br.id=bur.roleId where bur.userId=#{userId}")
    public List<String> getUserRole(Long userId);

    //手机验证
    @Select("select * from base_user where tel=#{tel}")
    public Map<String, Object> getUserByTel(String tel);

    //邮箱
    @Select("select * from base_user where email=#{tel}")
    public Map<String, Object> getUserByEmail(String tel);

    //注册
    @Insert("insert into base_user(userName,loginName,passWord,tel,sex) values(#{userName},#{loginName},#{passWord},#{tel},#{sex})")
    public Boolean getAddUser(User user);
    @Update("update base_user set passWord=#{passWord} where tel=#{tel}")
    public Boolean getUpdateUser(User user);

}
package com.zbf.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
/* *
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 9:03
  *@Param
  *@return
  *@miaoshu  登录页面
**/

@Data
@Accessors
public class User {

    private Integer id;
    private String userName;
    private String loginName;
    private String passWord;
    private String tel;
    private String email;

    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

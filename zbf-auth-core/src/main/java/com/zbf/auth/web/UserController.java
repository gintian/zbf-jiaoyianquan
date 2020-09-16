package com.zbf.auth.web;

import com.zbf.auth.mapper.UserDao;
import com.zbf.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* *
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 9:01
  *@Param
  *@return
  *@miaoshu
**/
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;


    @RequestMapping("/UserAdd")
    public Boolean getAddUser(@RequestBody User user) {
        System.out.println("注册的信息："+user);
        return userDao.getAddUser(user);
    }

    @RequestMapping("/Update")
    public Boolean getUpdateUser(@RequestBody User user) {
        return userDao.getUpdateUser(user);
    }
}

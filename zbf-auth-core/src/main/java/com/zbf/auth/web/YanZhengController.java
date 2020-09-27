package com.zbf.auth.web;


import com.zbf.auth.mapper.GetCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/* *
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 9:02
  *@Param
  *@return
  *@miaoshu  验证
**/
@RestController
@ResponseBody
public class YanZhengController {

    @Autowired
    private GetCodeDao getCodeDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/yan")
    public boolean getcode(String tel){
        System.out.println("____________++++==++++____________");
        System.out.println(tel);
        boolean b = getCodeDao.sendCode(tel);
        System.out.println(b);
        return b;
    }

    @RequestMapping("toNext")
    public boolean toNext(@RequestParam("password")String password){
        String pas = redisTemplate.opsForValue().get("code");
        if (pas.equals(pas)){
            return true;
        }else{
            return false;
        }
    }
}

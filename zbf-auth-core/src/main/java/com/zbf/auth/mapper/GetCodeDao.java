package com.zbf.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/* *
  *@Author tongdaowei
  *@Description //TODO
  *@Date 2020/9/16 0016 上午 9:00
  *@Param
  *@return
  *@miaoshu  短信
**/
@FeignClient(value = "zbf-sms")
public interface GetCodeDao {

    @RequestMapping("/yanzheng/sendCode")
    public boolean sendCode(@RequestParam("tel")String tel);

}

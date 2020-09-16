package com.zjj.controller;

import com.zbf.common.entity.CommonResult;
import com.zbf.common.entity.RandomUtil;
import com.zjj.RestTemplate.RestTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendTestController {


    @Value("${message.sid}")
    private String sid;

    @Value("${message.appId}")
    private String appId;

    @Value("${message.token}")
    private String token;

    @Value("${message.templateId}")
    private String templateId;


    @RequestMapping("sendMessage")
    public CommonResult loginSendMessage( CommonResult commonResult){
        System.out.println(commonResult.getPhoneNum());
//        try{
//            RestTest.testSendSms(sid,token,appId
//                    ,templateId,commonResult.getVerificationCode(),commonResult.getPhoneNum(),commonResult.getLoginName());
//            return new CommonResult(200,"短信发送成功！！！");
//
//        }catch (RuntimeException e){
//            e.printStackTrace();
//            return new CommonResult(400,"短信发送失败！！！");
//        }
        //生成4位验证码
        int[] randomNum = RandomUtil.getRandomNum(4);
        //将生成的验证码转为字符串
        System.out.println(randomNum);
        String s = "";
        for (int i = 0; i < randomNum.length; i++) {
            s+=randomNum[i];
        }
        System.out.println(s);
        //发送验证消息
        RestTest.testSendSms(sid,token,appId,templateId,s,commonResult.getPhoneNum(),"5");
        //封装返回消息
        return new CommonResult(s,200,"发送消息成功！！！");

    }

//    public static void main(String[] args) {
//        RestTest.testSendSms("1378fbc94dfc4924feac4265b065a394","70342cf35970f29f02be3c969b8bcb1d","9f89a5f9603f4bf4a88cb70730655726"
//                ,"565571","pipi","15751454215","5");
//    }





}

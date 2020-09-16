package com.zbf.common.entity;

import lombok.Data;

@Data
public class CommonResult<T> {

    /**
     * 返回值
     * 200 成功！！
     * 444 失败！！
     */
    private Integer code;

    /**
     * 返回的成功与失败的消息
     */
    private String msg;

    /**
     * 发送消息的uid
     */
    private String loginName;

    /**
     * 发短信的手机号
     */
    private String phoneNum;

    /**
     * 发送的验证码
     */
    private String verificationCode;


    public CommonResult(String verificationCode,Integer code, String msg) {
        this.verificationCode = verificationCode;
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

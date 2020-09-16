package com.zbf.common.entity;

import lombok.Data;

/**
 * 作者：LCG
 * 创建时间：2018/11/23 15:57
 * 描述：返回结果映射
 */
@Data
public class ResponseResult {

    /**
     * 返回信息编码  0失败 1成功
     */
    private int code;
    /**
     * 错误信息
     */
    private String error;
    /**
     * 程序返回结果
     */
    private Object result;
    /**
     * 成功信息
     */
    private String success;

    /**
     * 用户信息
     */
    private Object userInfo;

    /**
     *设置附加信息
     */
    private String msg;
    /**
     * 创建实例
     * @return
     */
    public static ResponseResult getResponseResult(){
        return new ResponseResult();
    }

    /**
     * 登陆成功的标识(这里存储了一些用户的信息)
     */
    private String token;
    /**
     * 用来表示token的一个唯一的字符串
     */
    private String tokenkey;

    /**
     * 选中的需要回显的菜单ID
     */
    private Long[] menuIds;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }
}

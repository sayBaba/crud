package com.hz.crud.vo;

/**
 * 发送邮件返回对象
 */
public class SendEmailRlt {

    private String code; // 0代表成功，-1失败
    private String msg; // 提示信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }




}

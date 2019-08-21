package com.hz.crud.vo;

/**
 * 发送邮件参数
 */
public class SendEmailVo {

    private String text; //邮件内容
    private String subject; //邮件主题
    private String emailAdress; //收件人

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }


}

package com.hz.crud.req;

import java.io.Serializable;

public class LoginReq implements Serializable {

    private String mail;

    private String userName;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}

package com.hz.crud.service;

import com.hz.crud.req.LoginReq;
import com.hz.crud.resp.UserResp;

import java.util.List;

public interface ITestService {

    public List<UserResp> getTestInfo();

    public  int updateUserInfo(UserResp userResp);

    public void login(LoginReq loginReq);
}

package com.hz.crud.dao;

import com.hz.crud.model.UserBean;
import com.hz.crud.resp.UserResp;

import java.util.List;

public interface TestMapper {

    List<UserBean> queryLike();


    int updateUserInfo(UserResp userResp);
}

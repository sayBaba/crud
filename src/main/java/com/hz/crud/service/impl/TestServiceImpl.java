package com.hz.crud.service.impl;

import com.google.gson.Gson;
import com.hz.crud.dao.TestMapper;
import com.hz.crud.model.UserBean;
import com.hz.crud.req.LoginReq;
import com.hz.crud.resp.UserResp;
import com.hz.crud.service.IRedisService;
import com.hz.crud.service.ITestService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IRedisService iRedisService;

    @Override
    @Transactional
    public List<UserResp> getTestInfo() {
        System.out.println("--------------------");
        List<UserResp> userRespList = new ArrayList<UserResp>();
        List<UserBean> list = testMapper.queryLike();
        for(int i =0;i<list.size();i++){
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(list.get(i),userResp);
            userRespList.add(userResp);
        }
        System.out.println("-------list:"+ list.size());
        return userRespList;
    }

    @Override
    public int updateUserInfo(UserResp userResp) {
        return testMapper.updateUserInfo(userResp);
    }

    @Override
    public void login(LoginReq loginReq) {
        //调用dao的代码 //TODO
        String key = "111*";
        Set<String> stringSet = iRedisService.getKeysFromLocal(key);
        System.err.println("===========stringSet=== ="+stringSet);
        for(String s :stringSet){
            System.err.println("============== ="+s);
        }

//        iRedisService.set("loginReq", new Gson().toJson(loginReq));
        //放入消息到mq,需要提前配置xml文件
//        rabbitTemplate.convertAndSend("info",loginReq);
        System.out.println("----------end-----------");
    }
}

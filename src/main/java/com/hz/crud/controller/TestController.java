package com.hz.crud.controller;

import com.hz.crud.req.LoginReq;
import com.hz.crud.resp.UserResp;
import com.hz.crud.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

    @Autowired
    private ITestService iTestService;

    @RequestMapping("/test")
    public String firstController(Model map){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("----------------------");
        map.addAttribute("userList",iTestService.getTestInfo());
//        modelAndView.setViewName("user");
//        modelAndView.addObject("userList",iTestService.getTestInfo());
        return "user";
    }


    @RequestMapping("/updateUser")
    public String Update(UserResp userResp,Model map){
        System.out.println("---------------------:"+userResp.getUserId());
        map.addAttribute("userResp",userResp);
        return "updateUser";
    }

    @RequestMapping("/updateUserInfo")
    public String UpdateUserInfo(UserResp userResp,Model map){
        System.out.println("--------getUserId-------------:"+userResp.getUserId());
        System.out.println("--------getAddress-------------:"+userResp.getAddress());
        System.out.println("--------getEmail-------------:"+userResp.getEmail());

        int i = iTestService.updateUserInfo(userResp);
        map.addAttribute("userResp",userResp);
        return "updateUser";
    }

    /**
     * 1.注册请求
     *  注册完成的时候，往MQ里面存放消息
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(LoginReq req){
        System.out.println("注册请求........");
        iTestService.login(req);
        return "1";
    }


}

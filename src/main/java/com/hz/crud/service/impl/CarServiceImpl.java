package com.hz.crud.service.impl;

import com.hz.crud.service.ICartService;
import com.hz.crud.service.IRedisService;
import com.hz.crud.vo.CartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

public class CarServiceImpl implements ICartService {

    private static final Logger loger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private IRedisService iRedisService;

    @Override
    public int addCart(String userId, String productId, int num) {
        loger.info("userId:{},productId:{}添加购物车请求.....");
        String redisKey = userId+"_"+productId;
        String data = iRedisService.get("redisKey");
        if (!StringUtils.isEmpty(data)){

            return 0;
        }


        return 0;
    }

    @Override
    public List<CartDto> getCartList(String userId) {
        return null;
    }

    @Override
    public int updateCartNum(String userId, String productId, int num) {
        return 0;
    }
}

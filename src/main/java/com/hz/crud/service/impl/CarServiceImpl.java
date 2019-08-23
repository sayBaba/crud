package com.hz.crud.service.impl;

import com.google.gson.Gson;
import com.hz.crud.dao.CartMapper;
import com.hz.crud.model.GoodsInfo;
import com.hz.crud.resp.CartResp;
import com.hz.crud.service.ICartService;
import com.hz.crud.service.IRedisService;
import com.hz.crud.vo.CartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements ICartService {

    private static final Logger loger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private CartMapper cartMapper;


    @Override
    public List<CartDto> getCartList(String userId) {
        loger.info("接受到userId:{}加载购物车请求.....");
        List<CartDto> cartDtoList = new ArrayList<CartDto>();
        Set<String> stringSet = iRedisService.getKeysFromLocal(userId+"*");
        //没有商品
        if(stringSet == null){
            return cartDtoList;
        }

        for (String s:stringSet){
            //根据key查询，redis中的值
            String str = iRedisService.get(s);
            loger.info("str:{}",str);
            //字符串json转bean
            CartDto cartDto = new Gson().fromJson(str,CartDto.class);
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }


    @Override
    public CartResp addCart(String userId, String goodsId, int num) {
        loger.info("userId:{},productId:{}添加购物车请求.....");
        String redisKey = userId+"_"+goodsId;
        //查询redis，商品是否已经存在
        String data = iRedisService.get("redisKey");
        Gson gson = new Gson();
        CartDto cartDto = null;
        //redis已经存在商品，只需要修改商品数量
        if (!StringUtils.isEmpty(data)){
            cartDto = gson.fromJson(data,CartDto.class);
            //修改库存数量
            int dbNum = Integer.parseInt(cartDto.getGoodsNum());
            cartDto.setGoodsNum(dbNum+num+"");

        }else{
            GoodsInfo goodsInfo = cartMapper.queryByGoodsId(goodsId);
            if (goodsInfo == null){
                return CartResp.getFail();
            }
            cartDto = new CartDto();
            //复制给新的javbean
            BeanUtils.copyProperties(goodsInfo,cartDto);
            cartDto.setGoodsNum(String.valueOf(num));
        }

        //设置购物车的数量
        iRedisService.set(redisKey,new Gson().toJson(cartDto));
        return CartResp.getSuc();
    }


    @Override
    public int updateCartNum(String userId, String productId, int num) {
        return 0;
    }
}

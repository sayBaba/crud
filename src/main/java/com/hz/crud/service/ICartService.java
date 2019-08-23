package com.hz.crud.service;

import com.hz.crud.resp.CartResp;
import com.hz.crud.vo.CartDto;

import java.util.List;

/**
 * 购物车
 */
public interface ICartService {


    /**
     * 加载购物车列表
     * @param userId 用户id
     * @return
     */
    List<CartDto> getCartList(String userId);


    /**
     * 加入购物车
     * @param userId
     * @param goodsId
     * @param num
     * @return
     */
    CartResp addCart(String userId, String goodsId, int num);

    /**
     * 修改数量
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    int updateCartNum(String userId,String productId,int num);
}

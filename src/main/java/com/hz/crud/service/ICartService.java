package com.hz.crud.service;

import com.hz.crud.vo.CartDto;

import java.util.List;

/**
 * 购物车
 */
public interface ICartService {

    /**
     * 加入购物车
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    int addCart(String userId,String productId,int num);

    /**
     * 购物车列表
     * @param userId
     * @return
     */
    List<CartDto> getCartList(String userId);

    /**
     * 修改数量
     * @param userId
     * @param productId
     * @param num
     * @return
     */
    int updateCartNum(String userId,String productId,int num);
}

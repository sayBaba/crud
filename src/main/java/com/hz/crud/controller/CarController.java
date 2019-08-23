package com.hz.crud.controller;

import com.hz.crud.req.AddCartReq;
import com.hz.crud.req.LoginReq;
import com.hz.crud.resp.CartResp;
import com.hz.crud.service.ICartService;
import com.hz.crud.vo.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 购物车入口
 */
@Controller
public class CarController {

    @Autowired
    private ICartService iCartService;



    /**
     * 添加购物车
     * @return
     */
    @ResponseBody
    @RequestMapping("/cart/addCart")
    public CartResp addCart(AddCartReq addCartReq){
        System.err.println("-----------------"+addCartReq.getGoodsId());
        System.err.println("-----------------"+addCartReq.getGoodsNum());
        System.err.println("-----------------"+addCartReq.getUserId());
        CartResp cartResp = iCartService.addCart(addCartReq.getUserId(),addCartReq.getGoodsId(),addCartReq.getGoodsNum());
        cartResp.setUserId(addCartReq.getUserId());
        return cartResp;
    }

    /**
     * 加载购物车
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/cart/loadCart")
    public CartResp loadCart(String userId){
        CartResp cartResp = new CartResp();
        List<CartDto> cartDtoList = iCartService.getCartList(userId);
        cartResp.setCartDtoList(cartDtoList);
        cartResp.setUserId(userId);
        cartResp.setCode("0");
        cartResp.setMsg("请求成功");
        return cartResp;
    }


}

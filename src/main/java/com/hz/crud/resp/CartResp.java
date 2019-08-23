package com.hz.crud.resp;

import com.hz.crud.vo.CartDto;

import java.util.List;

public class CartResp {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartDto> getCartDtoList() {
        return cartDtoList;
    }

    public void setCartDtoList(List<CartDto> cartDtoList) {
        this.cartDtoList = cartDtoList;
    }

    private String code; //请求是否成功，0-成功，1-失败
    private String msg; //返回状态描述
    private String userId; //用户Id

    private List<CartDto> cartDtoList;

    /**
     * 返回成功
     * @return
     */
    public static CartResp getSuc(){
        CartResp resp = new CartResp();
        resp.setMsg("请求成功");
        resp.setCode("0");
        return resp;
    }

    /**
     * 返回失败
     * @return
     */
    public static CartResp getFail(){
        CartResp resp = new CartResp();
        resp.setMsg("请求失败");
        resp.setCode("-1");
        return resp;
    }




}

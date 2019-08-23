package com.hz.crud.dao;

import com.hz.crud.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {

    /**
     * 根据商品Id查询到商品信息
     * @param goodsId
     * @return
     */
    GoodsInfo queryByGoodsId(@Param("goodsId") String goodsId);
}

package com.example.shiroredissession.service;

import com.example.shiroredissession.entity.Goods;

import java.util.List;

public interface GoodsService {

    void saveOneGoods(Goods goods);

    void delOneGoodsById(Integer id);

    void updateOneGoodsById(Goods goods);

    Goods queryOneGoodsByName(String name);

    List<Goods> queryAllGoods();
}

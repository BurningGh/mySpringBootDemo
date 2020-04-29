package com.example.shiroredissession.dao;

import com.example.shiroredissession.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDao {

    void saveOneGoods(Goods goods);

    void delOneGoodsById(Integer id);

    void updateOneGoodsById(Goods goods);

    Goods queryOneGoodsByName(String name);

    List<Goods> queryAllGoods();
}

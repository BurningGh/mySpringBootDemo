package com.example.shiroredissession.service.impl;


import com.example.shiroredissession.dao.GoodsDao;
import com.example.shiroredissession.entity.Goods;
import com.example.shiroredissession.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    private final GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public void saveOneGoods(Goods goods) {
        goodsDao.saveOneGoods(goods);
    }

    @Override
    public void delOneGoodsById(Integer id) {
        goodsDao.delOneGoodsById(id);
    }

    @Override
    public void updateOneGoodsById(Goods goods) {
        goodsDao.updateOneGoodsById(goods);
    }

    @Override
    public Goods queryOneGoodsByName(String name) {
        return goodsDao.queryOneGoodsByName(name);
    }

    @Override
    public List<Goods> queryAllGoods() {
        return goodsDao.queryAllGoods();
    }
}

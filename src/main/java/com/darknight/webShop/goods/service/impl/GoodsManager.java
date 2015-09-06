package com.darknight.webShop.goods.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goods.dao.GoodsDao;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by DarKnight on 2015/9/2.
 */
@Service
@Transactional(readOnly = true)
public class GoodsManager extends BaseManager<Goods, String> implements GoodsService {
    private GoodsDao goodsDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Goods, String> baseDao) {
        super.setBaseDao(baseDao);
        this.goodsDao = (GoodsDao)baseDao;
    }
}

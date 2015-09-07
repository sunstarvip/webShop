package com.darknight.webShop.goodsType.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goodsType.dao.GoodsTypeDao;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.goodsType.service.GoodsTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by DarKnight on 2015/9/7.
 */
@Service
@Transactional(readOnly = true)
public class GoodsTypeManager extends BaseManager<GoodsType, String> implements GoodsTypeService {
    private GoodsTypeDao goodsTypeDao;

    @Resource
    public void setBaseDao(BaseJpaDao<GoodsType, String> baseDao) {
        super.setBaseDao(baseDao);
        this.goodsTypeDao = (GoodsTypeDao)baseDao;
    }
}

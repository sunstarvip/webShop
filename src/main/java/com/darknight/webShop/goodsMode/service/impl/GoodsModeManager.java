package com.darknight.webShop.goodsMode.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goodsMode.dao.GoodsModeDao;
import com.darknight.webShop.goodsMode.entity.GoodsMode;
import com.darknight.webShop.goodsMode.service.GoodsModeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by DarKnight on 2015/9/2.
 */
@Service
@Transactional(readOnly = true)
public class GoodsModeManager extends BaseManager<GoodsMode, String> implements GoodsModeService {
    private GoodsModeDao goodsModeDao;

    @Resource
    public void setBaseDao(BaseJpaDao<GoodsMode, String> baseDao) {
        super.setBaseDao(baseDao);
        this.goodsModeDao = (GoodsModeDao)baseDao;
    }
}

package com.darknight.webShop.shop.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.shop.dao.ShopDao;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/8/30.
 */
@Service
@Transactional(readOnly = true)
public class ShopManager extends BaseManager<Shop, String> implements ShopService {
    private ShopDao shopDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Shop, String> baseDao) {
        super.setBaseDao(baseDao);
        this.shopDao = (ShopDao)baseDao;
    }
}

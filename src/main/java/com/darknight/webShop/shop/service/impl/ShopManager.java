package com.darknight.webShop.shop.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.shop.dao.ShopDao;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by DarKnight on 2015/8/30.
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

    @Override
    public Shop findShopByMerchantAccountName(String accountName) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("merchant", "merchant").add(Restrictions.eq("merchant.accountName", accountName));

        Shop shop = (Shop)criteria.uniqueResult();
        return shop;
    }
}

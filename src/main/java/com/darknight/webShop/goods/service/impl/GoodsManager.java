package com.darknight.webShop.goods.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goods.dao.GoodsDao;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 通过商店ID查询该商铺下所有未逻辑删除的商品
     * @param shopId 商店ID
     * @return
     */
    @Override
    public List<Goods> findVisibleGoodsListByShopId(String shopId) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("shop", "shop").add(Restrictions.eq("shop.id", shopId));
        criteria.addOrder(Order.desc("updateTime"));
        criteria.addOrder(Order.desc("createTime"));

        List<Goods> goodsList = criteria.list();
        return goodsList;
    }
}

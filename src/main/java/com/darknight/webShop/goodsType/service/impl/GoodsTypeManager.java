package com.darknight.webShop.goodsType.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goodsType.dao.GoodsTypeDao;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.goodsType.service.GoodsTypeService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 通过商店ID查询该商铺下所有未逻辑删除的商品类型
     * @param shopId 商店ID
     * @return
     */
    @Override
    public List<GoodsType> findVisibleGoodsTypeListByShopId(String shopId) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("shop", "shop").add(Restrictions.eq("shop.id", shopId));
        criteria.addOrder(Order.desc("updateTime"));
        criteria.addOrder(Order.desc("createTime"));

        List<GoodsType> typeList = criteria.list();
        return typeList;
    }

    @Override
    public List<GoodsType> findGoodsNumNullGoodsTypeListByShopId(String shopId) {
        return null;
    }
}

package com.darknight.webShop.goods.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.goods.dao.GoodsDao;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/9/2.
 */
@Service
@Transactional(readOnly = true)
public class GoodsManager extends BaseManager<Goods, String> implements GoodsService {
    private GoodsDao goodsDao;
    private ShopService shopService;

    @Resource
    public void setBaseDao(BaseJpaDao<Goods, String> baseDao) {
        super.setBaseDao(baseDao);
        this.goodsDao = (GoodsDao)baseDao;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * 通过商店ID查询该商铺下所有未逻辑删除的商品
     * @param shopId 商店ID
     * @return
     */
    @Override
    public List<Goods> findVisibleGoodsListByShopId(String shopId) {
//        Shop shop = shopService.find(shopId);

        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("shop", "shop").add(Restrictions.eq("shop.id", shopId));
//        switch(shop.getDisplayMode()) {
//            case Shop.DisplayMode.BY_TYPE:
//                criteria.setProjection(Projections.groupProperty("goodsType"));
//                break;
//        }
        criteria.addOrder(Order.desc("updateTime"));
        criteria.addOrder(Order.desc("createTime"));

        List<Goods> goodsList = criteria.list();
        return goodsList;
    }

    @Override
    public Object countVisibleGoodsByShopIdGroupByGoodsTypeIdIsNull(String shopId) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("shop", "shop").add(Restrictions.eq("shop.id", shopId));
        criteria.setProjection(Projections.rowCount());
        criteria.add(Restrictions.isNull("goodsType"));
        Object goodsNum = criteria.uniqueResult();

        return goodsNum;
    }

    @Override
    public List<Map> countVisibleGoodsByShopIdGroupByGoodsTypeId(String shopId) {
        // 组装统计查询条件
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.groupProperty("goodsType"));
        projectionList.add(Projections.rowCount(), "goodsNum");

        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.createAlias("shop", "shop").add(Restrictions.eq("shop.id", shopId));
        criteria.createAlias("goodsType", "goodsType", JoinType.FULL_JOIN);
        criteria.setProjection(projectionList);
        criteria.addOrder(Order.desc("goodsType.updateTime"));
        criteria.addOrder(Order.desc("goodsType.createTime"));

        List<Map> resultMapList = criteria.list();

        return resultMapList;
    }
}

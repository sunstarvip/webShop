package com.darknight.webShop.goods.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.goods.entity.Goods;

import java.util.List;

/**
 * Created by DarKnight on 2015/9/2.
 */
public interface GoodsService extends BaseService<Goods, String> {
    /**
     * 通过商店ID查询该商铺下所有未逻辑删除的商品
     * @param shopId 商店ID
     * @return
     */
    List<Goods> findVisibleGoodsListByShopId(String shopId);
}

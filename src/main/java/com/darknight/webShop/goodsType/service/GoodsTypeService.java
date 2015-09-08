package com.darknight.webShop.goodsType.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.goodsType.entity.GoodsType;

import java.util.List;

/**
 * Created by DarKnight on 2015/9/7.
 */
public interface GoodsTypeService extends BaseService<GoodsType, String> {

    /**
     * 通过商店ID查询该商铺下所有未逻辑删除的商品类型
     * @param shopId 商店ID
     * @return
     */
    List<GoodsType> findVisibleGoodsTypeListByShopId(String shopId);
}

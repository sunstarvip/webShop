package com.darknight.webShop.goodsType.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.goodsType.entity.GoodsType;

import java.util.List;

/**
 * Created by DarKnight on 2015/9/7.
 */
public interface GoodsTypeService extends BaseService<GoodsType, String> {

    List<GoodsType> findVisibleGoodsTypeListByShopId(String shopId);
}
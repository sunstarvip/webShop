package com.darknight.webShop.goods.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.goods.entity.Goods;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过商店ID统计该店铺下所有未逻辑删除的商品中
     * 未分类的商品数量
     * @param shopId
     * @return
     */
    Object countVisibleGoodsByShopIdGroupByGoodsTypeIdIsNull(String shopId);

    /**
     * 通过商店ID统计该店铺下所有未逻辑删除的商品
     * 按商品类型分组后，各组所含商品数量
     * @param shopId 商店ID
     * @return
     */
    List<Map> countVisibleGoodsByShopIdGroupByGoodsTypeId(String shopId);

    /**
     * 通过商店ID查询该店铺中指定商品类型ID下所有未逻辑删除的商品
     * @param shopId 商店ID
     * @param goodTypeId 商品类型ID
     * @return
     */
    List<Goods> findVisibleGoodsListByShopIdAndGoodsTypeId(String shopId, String goodTypeId);
}

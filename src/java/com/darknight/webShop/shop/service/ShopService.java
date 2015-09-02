package com.darknight.webShop.shop.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.shop.entity.Shop;

/**
 * Created by DarKnight on 2015/8/30.
 */
public interface ShopService extends BaseService<Shop, String> {
    Shop findShopByMerchantAccountName(String accountName);
}

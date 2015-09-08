package com.darknight.webShop.system.service;


import com.darknight.webShop.merchant.entity.Merchant;
import com.darknight.webShop.shop.entity.Shop;

import java.util.Map;

/**
 * Created by DarKnight on 2015/8/31.
 */
public interface ShopSystemService {
    /**
     * 店主用户登录并返回其对象
     * @param accountName 用户账号
     * @param accountPwd 用户密码
     * @return
     */
    Merchant loginMerchant(String accountName, String accountPwd);

    /**
     * 将店主对象实体生成登录用户信息Map
     * @param merchant 店主对象实体
     * @return
     */
    Map getLoginUserMap(Merchant merchant);

    /**
     * 生成店主对应的店铺ID
     * @param merchant 店主对象实体
     * @return
     */
    String getCurrentShopId(Merchant merchant);
}

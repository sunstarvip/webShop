package com.darknight.webShop.merchant.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.webShop.merchant.entity.Merchant;

/**
 * Created by Administrator on 2015/8/31.
 */
public interface MerchantService extends BaseService<Merchant, String> {
    Merchant findVisibleMerchantByAccountName(String accountName);
}

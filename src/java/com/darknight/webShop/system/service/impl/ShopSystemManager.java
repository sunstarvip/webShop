package com.darknight.webShop.system.service.impl;

import com.darknight.webShop.merchant.entity.Merchant;
import com.darknight.webShop.merchant.service.MerchantService;
import com.darknight.webShop.system.service.ShopSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/8/31.
 */
@Service
@Transactional(readOnly = true)
public class ShopSystemManager implements ShopSystemService {
    private MerchantService merchantService;

    @Resource
    public void setMerchantService(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @Override
    public String loginMerchant(String accountName, String accountPwd) {
        String merchantAccount = null;
        Merchant merchant = merchantService.findVisibleMerchantByAccountName(accountName);
        if(merchant != null) {
            if(StringUtils.equals(accountPwd, merchant.getAccountPwd())) {
                merchantAccount = merchant.getAccountName();
            }
        }

        return merchantAccount;
    }
}

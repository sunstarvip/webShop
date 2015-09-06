package com.darknight.webShop.system.service.impl;

import com.darknight.webShop.merchant.entity.Merchant;
import com.darknight.webShop.merchant.service.MerchantService;
import com.darknight.webShop.system.service.ShopSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DarKnight on 2015/8/31.
 */
@Service
@Transactional(readOnly = true)
public class ShopSystemManager implements ShopSystemService {
    private MerchantService merchantService;

    @Resource
    public void setMerchantService(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /**
     * 店主用户登录并返回其对象
     * @param accountName 用户账号
     * @param accountPwd 用户密码
     * @return
     */
    @Override
    public Merchant loginMerchant(String accountName, String accountPwd) {
        Merchant merchant = merchantService.findVisibleMerchantByAccountName(accountName);
        if(merchant != null) {
            if(StringUtils.equals(accountPwd, merchant.getAccountPwd())) {
                return merchant;
            }
        }

        return null;
    }

    /**
     * 将店主对象实体生成登录用户信息Map
     * @param merchant 店主对象实体
     * @return
     */
    @Override
    public Map getLoginUserMap(Merchant merchant) {
        Map loginUser = new HashMap();
        loginUser.put("merchantAccount", merchant.getAccountName());
        loginUser.put("merchantName", merchant.getName());
        loginUser.put("merchantEmail", merchant.getEmailAddress());
        loginUser.put("merchantContactNum", merchant.getContactNum());
        loginUser.put("merchantDescription", merchant.getDescription());

        return loginUser;
    }
}

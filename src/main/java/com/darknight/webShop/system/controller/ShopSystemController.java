package com.darknight.webShop.system.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.merchant.entity.Merchant;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import com.darknight.webShop.system.service.ShopSystemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 系统功能管理
 * Created by DarKnight on 2015/8/31.
 */
@RestController
public class ShopSystemController {
    private ShopSystemService shopSystemService;

    @Resource
    public void setShopSystemService(ShopSystemService shopSystemService) {
        this.shopSystemService = shopSystemService;
    }

    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(ShopSystemController.class);

    @RequestMapping(value = {"login"}, method = {RequestMethod.POST})
    public String login(String accountName, String accountPwd, HttpSession session) {
        ResultEntity resultData = new ResultEntity();

        if(StringUtils.isNotBlank(accountName) && StringUtils.isNotBlank(accountPwd)) {
            // 使用店家账号密码进行登录
            Merchant merchant = shopSystemService.loginMerchant(accountName, accountPwd);
            // 当店家账号不为空时代表登录成功
            if(merchant != null) {
                // 将登陆信息保存至Session
                Map loginUser = shopSystemService.getLoginUserMap(merchant);
                String loginUserInfo = JSON.toJSONString(loginUser);
                session.setAttribute("loginUserInfo", loginUserInfo);

                // 将该店主的店铺ID保存至Session
                String currentShopId = shopSystemService.getCurrentShopId(merchant);
                session.setAttribute("currentShopId", currentShopId);

                resultData.setStatus(ResultEntity.Status.SUCCESS);
            }
        }

        return JSON.toJSONString(resultData);
    }

}

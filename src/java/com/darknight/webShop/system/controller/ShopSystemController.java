package com.darknight.webShop.system.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.system.service.ShopSystemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/8/31.
 */
@RestController
@RequestMapping(value = "webShop/system")
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
            String merchantAccount = shopSystemService.loginMerchant(accountName, accountPwd);
            // 当店家账号不为空时代表登录成功
            if(StringUtils.isNotBlank(merchantAccount)) {
                resultData.setStatus(ResultEntity.Status.SUCCESS);

                // 将登陆信息保存至Session
                session.setAttribute("merchantAccount", merchantAccount);
            }
        }

        return JSON.toJSONString(resultData);
    }
}

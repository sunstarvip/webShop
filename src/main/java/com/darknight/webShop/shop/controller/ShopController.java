package com.darknight.webShop.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 店铺数据处理
 * Created by DarKnight on 2015/9/1.
 */
@RestController
@RequestMapping(value = "webShop/shop")
public class ShopController {
    private ShopService shopService;

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = {"managerShop"}, method = {RequestMethod.GET})
    public String managerShop(HttpSession session) {
        ResultEntity resultData = new ResultEntity();

        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        String shopInfo = JSON.toJSONString(shop);

        resultData.setDataInfo(shopInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

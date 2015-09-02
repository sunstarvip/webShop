package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 商品页面管理
 * Created by DarKnight on 2015/9/2.
 */
@Controller
@RequestMapping(value = "webShop/goods")
public class GoodsPageController {
    private GoodsService goodsService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value={"createPage"}, method={RequestMethod.GET})
    public String createPage() {
        return "webShop/goods/editPage";
    }

    @RequestMapping(value={"editPage"}, method={RequestMethod.GET})
    public String editPage(String goodsId, Model model) {
        Goods goods = goodsService.find(goodsId);
        String goodsInfo = JSON.toJSONString(goods);

        model.addAttribute("goodsInfo", goodsInfo);
        return "webShop/goods/editPage";
    }
}

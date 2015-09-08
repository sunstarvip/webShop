package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;

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

    @RequestMapping(value={"listPage"}, method={RequestMethod.GET})
    public String listPage() {
        return "webShop/goods/listPage";
    }

    @RequestMapping(value={"createPage"}, method={RequestMethod.GET})
    public String createPage() {
        return "webShop/goods/createPage";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(Goods goods) {

        goods.setCreateTime(new Date());
        goodsService.save(goods);

        return "redirect:/indexPage";
    }

    @RequestMapping(value={"updatePage"}, method={RequestMethod.GET})
    public String updatePage(String goodsId, Model model) {
        model.addAttribute("goodsId", goodsId);

        return "webShop/goods/updatePage";
    }
}

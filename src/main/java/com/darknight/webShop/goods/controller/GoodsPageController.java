package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 商品页面管理
 * Created by DarKnight on 2015/9/2.
 */
@Controller
@RequestMapping(value = "webShop/goods")
@SessionAttributes("currentShopId")
public class GoodsPageController {
    private GoodsService goodsService;
    private ShopService shopService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value={"listPage"}, method={RequestMethod.GET})
    public String listPage(@ModelAttribute("currentShopId")String currentShopId) {
        Shop shop = shopService.find(currentShopId);
        switch(shop.getDisplayMode()) {
            case Shop.DisplayMode.BY_DATE:
                return "webShop/goods/listByDatePage";
            case Shop.DisplayMode.BY_TYPE:
                return "webShop/goods/listByTypePage";
        }
        return "webShop/goods/listPage";
    }

    @RequestMapping(value={"displayPage"}, method={RequestMethod.GET})
    public String displayPage() {
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

package com.darknight.webShop.shop.controller;

import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 店铺页面管理
 * Created by DarKnight on 2015/9/1.
 */
@Controller
@RequestMapping(value = "webShop/shop")
public class ShopPageController {
    private ShopService shopService;

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value={"managerPage"}, method={RequestMethod.GET})
    public String managerPage() {
        return "webShop/shop/managerPage";
    }

    @RequestMapping(value={"editNamePage"}, method={RequestMethod.GET})
    public String editNamePage(String shopId, Model model) {
        Shop shop = shopService.find(shopId);

        model.addAttribute("shopId", shop.getId());
        model.addAttribute("shopName", shop.getName());
        return "webShop/shop/editNamePage";
    }

    @RequestMapping(value={"editName"}, method={RequestMethod.POST})
    public String editName(String shopId, String name) {
        Shop shop = shopService.find(shopId);
        shop.setUpdateTime(new Date());
        shop.setName(name);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }

    @RequestMapping(value={"editDescPage"}, method={RequestMethod.GET})
    public String editDescPage(String shopId, Model model) {
        Shop shop = shopService.find(shopId);

        model.addAttribute("shopId", shop.getId());
        model.addAttribute("shopDesc", shop.getDescription());
        return "webShop/shop/editDescPage";
    }

    @RequestMapping(value={"editDesc"}, method={RequestMethod.POST})
    public String editDesc(String shopId, String description) {
        Shop shop = shopService.find(shopId);
        shop.setUpdateTime(new Date());
        shop.setDescription(description);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }
}

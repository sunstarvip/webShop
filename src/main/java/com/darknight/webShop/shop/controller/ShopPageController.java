package com.darknight.webShop.shop.controller;

import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 店铺页面管理
 * Created by DarKnight on 2015/9/1.
 */
@Controller
@RequestMapping(value = "webShop/shop")
@SessionAttributes("currentShopId")
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
    public String editNamePage(Model model, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);

        model.addAttribute("shopName", shop.getName());
        return "webShop/shop/editNamePage";
    }

    @RequestMapping(value={"editName"}, method={RequestMethod.POST})
    public String editName(String name, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        shop.setUpdateTime(new Date());
        shop.setName(name);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }

    @RequestMapping(value={"editDescPage"}, method={RequestMethod.GET})
    public String editDescPage(Model model, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);

        model.addAttribute("shopDesc", shop.getDescription());
        return "webShop/shop/editDescPage";
    }

    @RequestMapping(value={"editDesc"}, method={RequestMethod.POST})
    public String editDesc(String description, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        shop.setUpdateTime(new Date());
        shop.setDescription(description);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }

    @RequestMapping(value={"editPicPage"}, method={RequestMethod.GET})
    public String editPicPage(Model model, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);

        model.addAttribute("shopName", shop.getName());
        model.addAttribute("shopPicUrl", shop.getPicUrl());
        return "webShop/shop/editPicPage";
    }

    @RequestMapping(value={"editPic"}, method={RequestMethod.POST})
    public String editPic(String picUrl, HttpSession session) {
        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        shop.setUpdateTime(new Date());
        shop.setPicUrl(picUrl);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }

    @RequestMapping(value={"editBuyModePage"}, method={RequestMethod.GET})
    public String editBuyModePage(Model model, @ModelAttribute("currentShopId")String currentShopId) {
//        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);

        model.addAttribute("shopBuyMode", shop.getBuyMode());
        return "webShop/shop/editBuyModePage";
    }

    @RequestMapping(value={"editBuyMode"}, method={RequestMethod.POST})
    public String editBuyMode(String description, @ModelAttribute("currentShopId")String currentShopId) {
//        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        shop.setUpdateTime(new Date());
        shop.setDescription(description);

        shopService.save(shop);
        return "redirect:/webShop/shop/managerPage";
    }
}

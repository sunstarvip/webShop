package com.darknight.webShop.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 店铺页面管理
 * Created by Administrator on 2015/9/1.
 */
@Controller
public class ShopPageController {
    @RequestMapping(value={"webShop/shop/managerPage"}, method={RequestMethod.GET})
    public String managerPage() {
        return "webShop/shop/managerPage";
    }
}

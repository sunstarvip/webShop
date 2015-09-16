package com.darknight.webShop.goodsType.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "page/goodsType")
public class GoodsTypePageController {

    @RequestMapping(value={"listPage"}, method={RequestMethod.GET})
    public String listPage() {
        return "webShop/goodsType/listPage";
    }

    @RequestMapping(value={"createPage"}, method={RequestMethod.GET})
    public String createPage() {
        return "webShop/goodsType/createPage";
    }

    @RequestMapping(value={"updatePage"}, method={RequestMethod.GET})
    public String updatePage(String goodsTypeId, Model model) {
        model.addAttribute("goodsTypeId", goodsTypeId);

        return "webShop/goodsType/updatePage";
    }
}

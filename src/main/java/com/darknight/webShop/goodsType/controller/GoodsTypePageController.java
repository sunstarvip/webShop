package com.darknight.webShop.goodsType.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "webShop/goodsType")
public class GoodsTypePageController {

    @RequestMapping(value={"createPage"}, method={RequestMethod.GET})
    public String createPage() {
        return "webShop/goodsType/createPage";
    }
}

package com.darknight.webShop.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/8/29.
 */
@Controller
public class ShopSystemPageController {
    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(ShopSystemPageController.class);

    @RequestMapping(value={"indexPage", "webShop/system/indexPage"}, method={RequestMethod.GET})
    public String indexPage() {
        return "webShop/system/indexPage";
    }

    @RequestMapping(value={"loginPage", "webShop/system/loginPage"}, method={RequestMethod.GET})
    public String loginPage(HttpServletRequest request, Model model, String targetUri) {
        // 将目标页面地址传递至登录页面，登录成功后继续访问目标页面
        model.addAttribute("targetUri", targetUri);
        return "webShop/system/loginPage";
    }
}

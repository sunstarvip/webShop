package com.darknight.webShop.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 系统页面管理
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

    @RequestMapping(value={"loginPage"}, method={RequestMethod.GET})
    public String loginPage(String targetUri, HttpServletRequest request, Model model, HttpSession session) {
        // 当已登录用户进入登录页面时
        if(session.getAttribute("loginUserInfo") != null) {
            // 若存在目标跳转页面则进行跳转
            if(StringUtils.isNotBlank(targetUri)) {
                return "redirect:" + targetUri;
            }else {  // 若不存在目标跳转页面则跳转至首页
                return "redirect:indexPage";
            }
        }
        // 未登录用户进入登录页面时
        // 将目标页面地址传递至登录页面，登录成功后继续访问目标页面
        model.addAttribute("targetUri", targetUri);
        return "webShop/system/loginPage";
    }

    @RequestMapping(value = {"logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.invalidate();

        return "webShop/system/logoutPage";
    }
}

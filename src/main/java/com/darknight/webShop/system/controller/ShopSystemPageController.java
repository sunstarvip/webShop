package com.darknight.webShop.system.controller;

import org.apache.commons.codec.binary.Base64;
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
 * Created by DarKnight on 2015/8/29.
 */
@Controller
public class ShopSystemPageController {
    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(ShopSystemPageController.class);

    @RequestMapping(value={"indexPage", "page/system/indexPage"}, method={RequestMethod.GET})
    public String indexPage() {
        return "webShop/system/indexPage";
    }

    @RequestMapping(value={"loginPage"}, method={RequestMethod.GET})
    public String loginPage(String targetUri, String queryString, Model model, HttpSession session) {
        // 当请求参数存在时进行Base64解码
        if(StringUtils.isNotBlank(queryString)) {
            byte[] queryByte = Base64.decodeBase64(queryString);
            queryString = new String(queryByte);
        }
        // 当已登录用户进入登录页面时
        if(session.getAttribute("loginUserInfo") != null) {
            // 若存在目标跳转页面则进行跳转
            if(StringUtils.isNotBlank(targetUri)) {
                return "redirect:" + targetUri + "?" + queryString;
            }else {  // 若不存在目标跳转页面则跳转至首页
                return "redirect:indexPage";
            }
        }
        // 未登录用户进入登录页面时
        // 将目标页面地址传递至登录页面，登录成功后继续访问目标页面
        model.addAttribute("targetUri", targetUri);
        model.addAttribute("queryString", queryString);
        return "webShop/system/loginPage";
    }

    @RequestMapping(value = {"logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.invalidate();

        return "webShop/system/logoutPage";
    }
}

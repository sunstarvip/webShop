package com.darknight.webShop.system.controller;

import com.darknight.core.base.entity.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/8/31.
 */
@RestController
@RequestMapping(value={"webShop/system"})
public class ShopSystemController {
    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(ShopSystemController.class);

    @RequestMapping(value={"login"}, method={RequestMethod.GET})
    public String login(String accountName, String accountPwd, HttpServletRequest request) {
        ResultEntity resultData = new ResultEntity();

        if(StringUtils.isNotBlank(accountName) && StringUtils.isNotBlank(accountName)) {

        }

        return "webShop/system/loginPage";
    }
}

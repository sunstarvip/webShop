package com.darknight.webShop.goods.controller;

import com.darknight.webShop.goods.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品数据处理
 * Created by DarKnight on 2015/9/2.
 */
@RestController
@RequestMapping(value = "webShop/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
}

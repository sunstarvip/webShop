package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 商品数据处理
 * Created by DarKnight on 2015/9/2.
 */
@RestController
@RequestMapping(value = "webShop/goods")
public class GoodsController {
    private GoodsService goodsService;
    private ShopService shopService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @ModelAttribute("goods")
    public Goods getGoodsEntity(@RequestParam(value = "id", required = false)String id) {
        if (StringUtils.isNotBlank(id)) {
            return goodsService.find(id);
        }
        Goods goods = new Goods();
        return goods;
    }

    @RequestMapping(value={"getGoodsList"}, method={RequestMethod.GET})
    public String getGoodsList(HttpSession session) {
        ResultEntity resultData = new ResultEntity();

        String currentShopId = session.getAttribute("currentShopId").toString();
        List<Goods> goodsList = goodsService.findVisibleGoodsListByShopId(currentShopId);
        String goodsListInfo = JSON.toJSONString(goodsList);

        resultData.setDataInfo(goodsListInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"saveGoods"}, method={RequestMethod.POST})
    public String saveGoods(@ModelAttribute("goods")Goods goods, HttpSession session) {
        ResultEntity resultData = new ResultEntity();

        String currentShopId = session.getAttribute("currentShopId").toString();
        Shop shop = shopService.find(currentShopId);
        goods.setCreateTime(new Date());
        goods.setShop(shop);
        goodsService.save(goods);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"updateGoods"}, method={RequestMethod.POST})
    public String updateGoods(@ModelAttribute("goods")Goods goods) {
        ResultEntity resultData = new ResultEntity();

        goods.setUpdateTime(new Date());
        goodsService.save(goods);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

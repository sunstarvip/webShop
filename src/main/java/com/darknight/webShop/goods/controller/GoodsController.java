package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.goodsType.service.GoodsTypeService;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品数据处理
 * Created by DarKnight on 2015/9/2.
 */
@RestController
@RequestMapping(value = "webShop/goods")
@SessionAttributes("currentShopId")
public class GoodsController {
    private GoodsService goodsService;
    private ShopService shopService;
    private GoodsTypeService goodsTypeService;

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Resource
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @ModelAttribute("goods")
    public Goods getGoodsEntity(@RequestParam(value = "id", required = false)String id) {
        if (StringUtils.isNotBlank(id)) {
            return goodsService.find(id);
        }
        Goods goods = new Goods();
        return goods;
    }

    @RequestMapping(value={"getGoods"}, method={RequestMethod.GET})
    public String getGoods(@ModelAttribute("goods")Goods goods) {
        ResultEntity resultData = new ResultEntity();

        String goodsInfo;
        if(goods.getGoodsType() != null) {
            JSONObject goodsJson = (JSONObject)JSON.toJSON(goods);
            goodsJson.put("goodsTypeId", goods.getGoodsType().getId());
            goodsInfo = JSON.toJSONString(goodsJson);
        }else {
            goodsInfo = JSON.toJSONString(goods);
        }


        resultData.setDataInfo(goodsInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"getGoodsList"}, method={RequestMethod.GET})
    public String getGoodsList(@ModelAttribute("currentShopId")String currentShopId) {
        ResultEntity resultData = new ResultEntity();

        Shop shop = shopService.find(currentShopId);
        String goodsListInfo = null;
        switch(shop.getDisplayMode()) {
            case Shop.DisplayMode.BY_DATE:
                List<Goods> goodsList = goodsService.findVisibleGoodsListByShopId(currentShopId);
                goodsListInfo = JSON.toJSONString(goodsList);
                break;
            case Shop.DisplayMode.BY_TYPE:
                List<Map> resultList = goodsService.countVisibleGoodsByShopIdGroupByGoodsTypeId(currentShopId);
                goodsListInfo = JSON.toJSONString(resultList);

//                Object resultList = goodsService.countVisibleGoodsByShopIdGroupByGoodsTypeIdIsNull(currentShopId);
//                goodsListInfo = JSON.toJSONString(resultList);
                break;
        }
//        List<Goods> goodsList = goodsService.findVisibleGoodsListByShopId(currentShopId);
//        String goodsListInfo = JSON.toJSONString(goodsList);

        resultData.setDataInfo(goodsListInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"saveGoods"}, method={RequestMethod.POST})
    public String saveGoods(@ModelAttribute("goods")Goods goods,
                            @ModelAttribute("currentShopId")String currentShopId) {
        ResultEntity resultData = new ResultEntity();

        Shop shop = shopService.find(currentShopId);
        goods.setCreateTime(new Date());
        goods.setShop(shop);
        goodsService.save(goods);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"updateGoods"}, method={RequestMethod.POST})
    public String updateGoods(@ModelAttribute("goods")Goods goods, String goodsTypeId) {
        ResultEntity resultData = new ResultEntity();

        goods.setUpdateTime(new Date());
        if(StringUtils.isNotBlank(goodsTypeId)) {
            if(goods.getGoodsType() == null || !StringUtils.equals(goods.getGoodsType().getId(), goodsTypeId)) {
                GoodsType goodsType = goodsTypeService.find(goodsTypeId);
                goods.setGoodsType(goodsType);
            }
        }else {
            goods.setGoodsType(null);
        }
        goodsService.save(goods);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

package com.darknight.webShop.goodsType.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.darknight.core.base.entity.ResultEntity;
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
 * Created by Administrator on 2015/9/7.
 */
@RestController
@RequestMapping(value = "rest/goodsType")
@SessionAttributes("currentShopId")
public class GoodsTypeController {
    private GoodsTypeService goodsTypeService;
    private ShopService shopService;
    private GoodsService goodsService;

    @Resource
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Resource
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ModelAttribute("goodsType")
    public GoodsType getGoodsTypeEntity(@RequestParam(value = "id", required = false)String id) {
        if (StringUtils.isNotBlank(id)) {
            return goodsTypeService.find(id);
        }
        GoodsType goodsType = new GoodsType();
        return goodsType;
    }

    @RequestMapping(value={"getGoodsType"}, method={RequestMethod.GET})
    public String getGoodsType(@ModelAttribute("goodsType")GoodsType goodsType) {
        ResultEntity resultData = new ResultEntity();

        String goodsTypeInfo = JSON.toJSONString(goodsType);

        resultData.setDataInfo(goodsTypeInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"getGoodsTypeList"}, method={RequestMethod.GET})
    public String getGoodsTypeList(@ModelAttribute("currentShopId")String currentShopId) {
        ResultEntity resultData = new ResultEntity();

        List<GoodsType> typeList = goodsTypeService.findVisibleGoodsTypeListByShopId(currentShopId);
        List<Map> resultList = goodsService.countVisibleGoodsByShopIdGroupByGoodsTypeId(currentShopId);
        String goodsListInfo = JSON.toJSONString(resultList);
        String typeListInfo = JSON.toJSONString(typeList);

        resultData.setDataInfo(typeListInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"saveGoodsType"}, method={RequestMethod.POST})
    public String saveGoodsType(@ModelAttribute("goodsType")GoodsType goodsType, @ModelAttribute("currentShopId")String currentShopId) {
        ResultEntity resultData = new ResultEntity();

        Shop shop = shopService.find(currentShopId);
        goodsType.setCreateTime(new Date());
        goodsType.setShop(shop);
        goodsTypeService.save(goodsType);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"updateGoodsType"}, method={RequestMethod.POST})
    public String updateGoodsType(@ModelAttribute("goodsType")GoodsType goodsType) {
        ResultEntity resultData = new ResultEntity();

        goodsType.setUpdateTime(new Date());
        goodsTypeService.save(goodsType);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

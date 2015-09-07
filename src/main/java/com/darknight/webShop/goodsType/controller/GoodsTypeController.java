package com.darknight.webShop.goodsType.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.goodsType.service.GoodsTypeService;
import com.darknight.webShop.shop.entity.Shop;
import com.darknight.webShop.shop.service.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
@RestController
@RequestMapping(value = "webShop/goodsType")
public class GoodsTypeController {
    private GoodsTypeService goodsTypeService;
    private ShopService shopService;

    @Resource
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @Resource
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value={"getGoodsTypeList"}, method={RequestMethod.GET})
    public String getGoodsTypeList(HttpSession session) {
        ResultEntity resultData = new ResultEntity();

        String loginUserInfo = session.getAttribute("loginUserInfo").toString();
        JSONObject loginUserJson = JSON.parseObject(loginUserInfo);
        String merchantAccount = loginUserJson.getString("merchantAccount");
        Shop shop = shopService.findShopByMerchantAccountName(merchantAccount);
        List<GoodsType> typeList = goodsTypeService.findVisibleGoodsTypeListByShopId(shop.getId());
        String typeListInfo = JSON.toJSONString(typeList);

        resultData.setDataInfo(typeListInfo);
        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value={"saveGoodsType"}, method={RequestMethod.POST})
    public String saveGoodsType(GoodsType goodsType) {
        ResultEntity resultData = new ResultEntity();

        goodsType.setCreateTime(new Date());
        goodsTypeService.save(goodsType);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

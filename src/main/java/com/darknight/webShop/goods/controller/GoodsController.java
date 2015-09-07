package com.darknight.webShop.goods.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.goods.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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

    @RequestMapping(value={"saveGoods"}, method={RequestMethod.POST})
    public String saveGoods(Goods goods) {
        ResultEntity resultData = new ResultEntity();

        goods.setCreateTime(new Date());
        goodsService.save(goods);

        resultData.setStatus(ResultEntity.Status.SUCCESS);

        return JSON.toJSONString(resultData);
    }
}

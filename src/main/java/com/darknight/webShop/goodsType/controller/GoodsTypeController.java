package com.darknight.webShop.goodsType.controller;

import com.alibaba.fastjson.JSON;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.webShop.goodsType.entity.GoodsType;
import com.darknight.webShop.goodsType.service.GoodsTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/7.
 */
@RestController
@RequestMapping(value = "webShop/goodsType")
public class GoodsTypeController {
    private GoodsTypeService goodsTypeService;

    @Resource
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
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

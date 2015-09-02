package com.darknight.webShop.goodsMode.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.webShop.goods.entity.Goods;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by DarKnight on 2015/9/2.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_goods_mode")
public class GoodsMode extends DefaultEntity {
    private String modeName;  // 型号名称
    private Integer modePrice;  // 价格
    private Integer stockNum;  // 库存数量

    private Goods goods;  // 对应商品

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Integer getModePrice() {
        return modePrice;
    }

    public void setModePrice(Integer modePrice) {
        this.modePrice = modePrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}

package com.darknight.webShop.goodsType.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.webShop.goods.entity.Goods;
import com.darknight.webShop.shop.entity.Shop;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 商品类型
 * Created by DarKnight on 2015/9/7.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_goods_type")
public class GoodsType extends DefaultEntity {
    private String typeName;  // 类型名称

    private Shop shop;  // 所属店铺
    private List<Goods> goodsList;  // 包含商品

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "goodsType")
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}

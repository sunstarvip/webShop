package com.darknight.webShop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.webShop.goodsMode.entity.GoodsMode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 商品
 * Created by DarKnight on 2015/9/2.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_goods")
public class Goods extends DefaultEntity {
    private String name;  // 商品名称
    private String description;  // 商品描述
    private String picUrl;  // 商品图片
    private String buyLink;  // 商品购买链接
    private String goodsStatus;  // 商品状态
    private Float goodsPrice;  // 商品价格
    private Integer stockNum;  // 库存数量

    private List<GoodsMode> modeList;  // 商品类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "goods")
    public List<GoodsMode> getModeList() {
        return modeList;
    }

    public void setModeList(List<GoodsMode> modeList) {
        this.modeList = modeList;
    }

    public interface GoodsStatus {
        public static final String ON_SALE = "ON_SALE";  // 出售中
        public static final String OFF_SHELVES = "OFF_SHELVES";  // 已下架
    }
}

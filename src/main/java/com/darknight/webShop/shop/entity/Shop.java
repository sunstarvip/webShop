package com.darknight.webShop.shop.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.webShop.merchant.entity.Merchant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 商店
 * Created by DarKnight on 2015/8/29.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_shop")
public class Shop extends DefaultEntity {
    private String name;
    private String description;  // 店铺描述
    private String picUrl;  // 店铺商标
    private String displayMode;  // 商品展示方式
    private String payMode;  // 商品支付方式

    private Merchant merchant;  // 店主用户

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

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public interface DisplayMode {
        static final String BY_DATE = "by_date";
        static final String BY_TYPE = "by_type";
    }

    public interface PayMode {
        static final String IN_SITE = "in_site";
        static final String OUT_SITE = "out_site";
    }
}
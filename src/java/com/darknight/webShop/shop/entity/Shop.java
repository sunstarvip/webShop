package com.darknight.webShop.shop.entity;

import com.darknight.core.base.entity.DefaultEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商店
 * Created by Administrator on 2015/8/29.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_shop")
public class Shop extends DefaultEntity {
    private String name;
    private String description;  // 店铺描述
    private String picUrl;  // 店铺商标

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
}

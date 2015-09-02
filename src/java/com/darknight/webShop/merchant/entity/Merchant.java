package com.darknight.webShop.merchant.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.webShop.shop.entity.Shop;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 店主
 * Created by DarKnight on 2015/8/30.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_webshop_merchant")
public class Merchant extends DefaultEntity {
    private String accountName;  // 账号
    private String accountPwd;  // 密码
    private String emailAddress;  // 邮箱地址
    private String contactNum;  // 联系电话
    private String name;  // 姓名
    private String description;  // 个人描述
    private String picUrl;  // 个人头像

    private List<Shop> shopList;  // 个人店铺列表

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

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

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "merchant")
    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}

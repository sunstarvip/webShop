package com.darknight.webShop.goodsType.entity;

import com.darknight.core.base.entity.DefaultEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

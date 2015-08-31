package com.darknight.webShop.merchant.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.merchant.entity.Merchant;
import com.darknight.webShop.merchant.dao.MerchantDao;
import com.darknight.webShop.merchant.service.MerchantService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/8/31.
 */
@Service
@Transactional(readOnly = true)
public class MerchantManager extends BaseManager<Merchant, String> implements MerchantService {
    private MerchantDao merchantDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Merchant, String> baseDao) {
        super.setBaseDao(baseDao);
        this.merchantDao = (MerchantDao)baseDao;
    }

    @Override
    public Merchant findVisibleMerchantByAccountName(String accountName) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
        criteria.add(Restrictions.eq("accountName", accountName));

        Merchant merchant = (Merchant)criteria.uniqueResult();
        return merchant;
    }
}

package com.darknight.webShop.upload.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.webShop.upload.dao.UploadFileDao;
import com.darknight.webShop.upload.entity.UploadFile;
import com.darknight.webShop.upload.service.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 处理上传文件数据库记录
 * Created by DarKnight on 2015/9/3.
 */
@Service
@Transactional(readOnly = true)
public class UploadFileManager extends BaseManager<UploadFile, String> implements UploadFileService {
    private UploadFileDao uploadFileDao;

    @Resource
    public void setBaseDao(BaseJpaDao<UploadFile, String> baseDao) {
        super.setBaseDao(baseDao);
        this.uploadFileDao = (UploadFileDao)baseDao;
    }
}

package com.darknight.webShop.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 处理上传文件IO操作
 * Created by DarKnight on 2015/9/2.
 */
public interface UploadService {
    boolean saveMultipartFile(MultipartFile multipartFile, String filePath);

    boolean saveMultipartFile(List<MultipartFile> fileList, String filePath);
}

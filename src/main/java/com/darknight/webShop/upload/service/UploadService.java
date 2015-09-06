package com.darknight.webShop.upload.service;

import com.darknight.webShop.upload.entity.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 处理上传文件IO操作
 * Created by DarKnight on 2015/9/2.
 */
public interface UploadService {
    UploadFile saveMultipartFile(MultipartFile multipartFile, String realPath, String filePath);

    List<UploadFile> saveMultipartFile(List<MultipartFile> fileList, String realPath, String filePath);
}

package com.darknight.webShop.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by DarKnight on 2015/9/2.
 */
public interface UploadService {
    boolean svaeMultipartFile(MultipartFile multipartFile, String filePath);
}

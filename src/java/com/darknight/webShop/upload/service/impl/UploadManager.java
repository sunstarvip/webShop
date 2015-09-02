package com.darknight.webShop.upload.service.impl;

import com.darknight.webShop.upload.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by DarKnight on 2015/9/2.
 */
@Service
public class UploadManager implements UploadService {
    @Override
    public boolean svaeMultipartFile(MultipartFile multipartFile, String filePath) {
        if(!multipartFile.isEmpty()) {
            // 生成最终文件路径
            filePath += "/" + multipartFile.getOriginalFilename();
            try {
                // 获取文件对象，没有时创建该对象
                File targetFile = FileUtils.getFile(filePath);
                if(!targetFile.exists()) {
//                    targetFile.createNewFile();
                    targetFile = new File(filePath);
                }
                multipartFile.transferTo(targetFile);
                return true;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

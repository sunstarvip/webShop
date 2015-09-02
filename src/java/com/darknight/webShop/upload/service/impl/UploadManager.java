package com.darknight.webShop.upload.service.impl;

import com.darknight.webShop.upload.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 处理上传文件IO操作
 * Created by DarKnight on 2015/9/2.
 */
@Service
public class UploadManager implements UploadService {
    @Override
    public boolean saveMultipartFile(MultipartFile multipartFile, String filePath) {
        if(!multipartFile.isEmpty()) {
            // 获取上传目录，没有时创建该目录
            File targetDir = FileUtils.getFile(filePath);
            if(!targetDir.exists()) {
                targetDir.mkdirs();
            }

            // 生成最终文件路径
            filePath += "/" + multipartFile.getOriginalFilename();
            try {
                // 获取文件对象，没有时创建该对象
                File targetFile = FileUtils.getFile(filePath);
                if(!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                multipartFile.transferTo(targetFile);
                return true;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean saveMultipartFile(List<MultipartFile> fileList, String filePath) {
        if(fileList != null && !fileList.isEmpty()) {
            // 获取上传目录，没有时创建该目录
            File targetDir = FileUtils.getFile(filePath);
            if(!targetDir.exists()) {
                targetDir.mkdirs();
            }

            for(MultipartFile multipartFile : fileList) {
                if(!multipartFile.isEmpty()) {
                    try {
                        // 获取文件对象，没有时创建该对象
                        File targetFile = FileUtils.getFile(filePath + "/" + multipartFile.getOriginalFilename());
                        if(!targetFile.exists()) {
                            targetFile.createNewFile();
                        }
                        multipartFile.transferTo(targetFile);
                        return true;
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}

package com.darknight.webShop.upload.service.impl;

import com.darknight.webShop.upload.entity.UploadFile;
import com.darknight.webShop.upload.service.UploadFileService;
import com.darknight.webShop.upload.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理上传文件IO操作
 * Created by DarKnight on 2015/9/2.
 */
@Service
public class UploadManager implements UploadService {
    private UploadFileService uploadFileService;

    @Resource
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @Override
    public UploadFile saveMultipartFile(MultipartFile multipartFile, String realPath, String filePath) {
        // 获取上传目录，没有时创建该目录
        File targetDir = FileUtils.getFile(realPath + filePath);
        if(!targetDir.exists()) {
            targetDir.mkdirs();
        }
        if(!multipartFile.isEmpty()) {
            try {
                // 获取文件对象，没有时创建该对象
                File targetFile = FileUtils.getFile(realPath + filePath + "/" + multipartFile.getOriginalFilename());
                if(!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                multipartFile.transferTo(targetFile);

                UploadFile uploadFile = new UploadFile(multipartFile, targetFile, filePath);
//                uploadFile = uploadFileService.save(uploadFile);

                return uploadFile;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<UploadFile> saveMultipartFile(List<MultipartFile> fileList, String realPath, String filePath) {
        List<UploadFile> uploadFileList = new ArrayList<UploadFile>();
        if(fileList != null && !fileList.isEmpty()) {
            // 获取上传目录，没有时创建该目录
            File targetDir = FileUtils.getFile(realPath + filePath);
            if(!targetDir.exists()) {
                targetDir.mkdirs();
            }

            for(MultipartFile multipartFile : fileList) {
                if(!multipartFile.isEmpty()) {
                    try {
                        // 获取文件对象，没有时创建该对象
                        File targetFile = FileUtils.getFile(realPath + filePath + "/" + multipartFile.getOriginalFilename());
                        if(!targetFile.exists()) {
                            targetFile.createNewFile();
                        }
                        multipartFile.transferTo(targetFile);

                        UploadFile uploadFile = new UploadFile(multipartFile, targetFile, filePath);
                        uploadFileList.add(uploadFile);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }

//            if(!uploadFileList.isEmpty()) {
//                uploadFileList = uploadFileService.save(uploadFileList);
//            }
        }
        return uploadFileList;
    }
}

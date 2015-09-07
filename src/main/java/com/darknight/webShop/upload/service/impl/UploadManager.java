package com.darknight.webShop.upload.service.impl;

import com.darknight.webShop.upload.entity.UploadFile;
import com.darknight.webShop.upload.service.UploadFileService;
import com.darknight.webShop.upload.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 处理上传文件IO操作
 * Created by DarKnight on 2015/9/2.
 */
@Service
public class UploadManager implements UploadService {
    private static Random randomNameBuilder = new Random();
    private UploadFileService uploadFileService;

    @Resource
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    private String getFileTypeByeFullFileName(String fullFileName) {
        int dotIndex = StringUtils.lastIndexOf(fullFileName, ".");
        String fileType = StringUtils.substring(fullFileName, dotIndex);

        return fileType;
    }

    private String makeTagetName(String originalFilename) {
        String targetName = Long.toString(Math.abs(randomNameBuilder.nextLong()));
        targetName += getFileTypeByeFullFileName(originalFilename);

        return targetName;
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
//                String targetFileName = multipartFile.getOriginalFilename();
                String targetFileName = makeTagetName(multipartFile.getOriginalFilename());
                File targetFile = FileUtils.getFile(realPath + filePath + "/" + targetFileName);
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
//                        File targetFile = FileUtils.getFile(realPath + filePath + "/" + multipartFile.getOriginalFilename());
                        File targetFile = FileUtils.getFile(realPath + filePath + "/" + makeTagetName(multipartFile.getOriginalFilename()));
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

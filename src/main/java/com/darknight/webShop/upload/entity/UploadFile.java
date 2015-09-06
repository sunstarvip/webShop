package com.darknight.webShop.upload.entity;

import com.darknight.core.base.entity.DefaultEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.File;
import java.util.Date;

/**
 * 上传文件
 * Created by DarKnight on 2015/9/3.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_upload_file")
public class UploadFile extends DefaultEntity {
    private String fileName;  // 文件名称
    private String fileType;  // 文件类型
    private String contentType;  // Content Type
    private String fullfileName;  // 文件名称+文件类型
    private String filePath;  // 文件相对路径
    private String absolutePath;  // 文件绝对路径
    private Long fileSize;  // 文件大小
    private String dirFlag;  // 是否是目录

    public UploadFile() {
    }

    public UploadFile(MultipartFile multipartFile, File targetFile, String filePath) {
        this.fullfileName = multipartFile.getOriginalFilename();
        this.fileName = getFileNameByeFullFileName(this.fullfileName);
        this.fileType = getFileTypeByeFullFileName(this.fullfileName);
        this.contentType = multipartFile.getContentType();
        this.fileSize = multipartFile.getSize();
        this.filePath = filePath + "/" + this.fullfileName;
        this.absolutePath = targetFile.getAbsolutePath();
        this.dirFlag = getDirFlagByTargetFile(targetFile);
        this.setCreateTime(new Date());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFullfileName() {
        return fullfileName;
    }

    public void setFullfileName(String fullfileName) {
        this.fullfileName = fullfileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDirFlag() {
        return dirFlag;
    }

    public void setDirFlag(String dirFlag) {
        this.dirFlag = dirFlag;
    }

    private String getFileNameByeFullFileName(String fullFileName) {
        int dotIndex = StringUtils.lastIndexOf(fullFileName, ".");
        String fileName = StringUtils.substring(fullFileName, 0, dotIndex);

        return fileName;
    }

    private String getFileTypeByeFullFileName(String fullFileName) {
        int dotIndex = StringUtils.lastIndexOf(fullFileName, ".");
        String fileType = StringUtils.substring(fullFileName, dotIndex + 1);

        return fileType;
    }

    private String getDirFlagByTargetFile(File targetFile) {
        if(targetFile.isDirectory()) {
            return DirFlag.YES;
        }

        return DirFlag.NO;
    }

    public interface FileSize {
        static final long EMPTY = 0;
    }

    public interface DirFlag {
        static final String YES = "YES";
        static final String NO = "NO";
    }
}

package com.darknight.webShop.upload.entity;

import com.darknight.core.base.entity.DefaultEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    private String fullfileName;  // 文件名称+文件类型
    private String filePath;  // 文件相对路径
    private String absolutePath;  // 文件绝对路径
    private int fileSize = 0;  // 文件大小
    private boolean isDir = IsDir.NO;  // 是否是目录

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

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    public interface IsDir {
        static final boolean YES = true;
        static final boolean NO = false;
    }
}

package com.darknight.webShop.upload.controller;

import com.darknight.webShop.upload.entity.UploadFile;
import com.darknight.webShop.upload.service.UploadService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 上传控制
 * Created by DarKnight on 2015/9/2.
 */
@RestController
@RequestMapping(value = "upload")
public class UploadController {
    private UploadService uploadService;

    @Resource
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value={"uploadFile"}, method={RequestMethod.POST})
    public String uploadFile(HttpServletRequest request) {
        // 检查是否有文件上传
        if(ServletFileUpload.isMultipartContent(request)) {
            // 获取服务器运行环境
            ServletContext ctx = request.getSession().getServletContext();
            // 获取web.xml中配置的临时文件夹路径
            String tempPath = ctx.getInitParameter("uploadTempPath");
            // 生成临时文件夹
            File tmpDir = new File(tempPath);

            // 生成临时文件处理工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设定从内存中将文件写入磁盘的界限大小为1M
            factory.setSizeThreshold(1 * 1024 * 1024);
            // 设定临时文件夹
            factory.setRepository(tmpDir);

            // 生成上传处理者
            ServletFileUpload upload = new ServletFileUpload(factory);

            // 获取生成上传路径
            String realPath = ctx.getRealPath("/");
            String uploadPath = realPath + ctx.getInitParameter("uploadPath");
            // 判断目标存放目录是否存在，不存在时则创建该目录
            if (!new File(uploadPath).isDirectory()) {
                new File(uploadPath).mkdirs();
            }
            try {
                // 获取文件组件列表
                List<FileItem> fileItems = upload.parseRequest(request);
                for(FileItem fileItem : fileItems) {
                    // 当是文件字段时进行保存
                    if(!fileItem.isFormField()){
                        // 获取文件名称
                        String fileName = fileItem.getName();
                        // 生成文件
                        File uploadedFile = new File(uploadPath + fileName);
                        fileItem.write(uploadedFile);
                    }
                }
            } catch(FileUploadException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "upload success!!";
    }

    @RequestMapping(value={"springUploadFile"}, method={RequestMethod.POST})
    public String springUploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取服务器运行环境
        ServletContext ctx = request.getSession().getServletContext();
        // 获取生成上传路径
        String realPath = ctx.getRealPath("/");
        String uploadPath = ctx.getInitParameter("uploadPath");

        List<MultipartFile> fileList = multipartRequest.getFiles("Filedata");
        if(!fileList.isEmpty()) {
            UploadFile uploadFile = uploadService.saveMultipartFile(fileList.get(0), realPath, uploadPath);
        }
        return "upload success!!";
    }

    @RequestMapping(value={"springUploadFileList"}, method={RequestMethod.POST})
    public String springUploadFileList(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取服务器运行环境
        ServletContext ctx = request.getSession().getServletContext();
        // 获取生成上传路径
        String realPath = ctx.getRealPath("/");
        String uploadPath = ctx.getInitParameter("uploadPath");

        List<MultipartFile> fileList = multipartRequest.getFiles("Filedata");
        List<UploadFile> uploadFileList = uploadService.saveMultipartFile(fileList, realPath, uploadPath);
        return "upload success!!";
    }
}

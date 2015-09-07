<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺管理</title>
    <link type="text/css" rel="stylesheet" href="${ctx }/static/plugins/uploadify/uploadify.css" />
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/uploadify/jquery.uploadify.min.js"></script>
</head>
<body>
    <div>
        <div>
            <form action="${ctx }/upload/springUploadFile" method="post" enctype="multipart/form-data">
                <input id="id" name="id" value="${shopId}">
                <input class="" id="picUrl" name="file" type="file" value="${shopPicUrl}">
                <button class="" id="editBtn" type="submit">修改</button>
            </form>
        </div>
    </div>

    <script>
        // 页面初始化
        $(function() {
            $('#picUrl').uploadify({
                'auto': true,  // 是否自动上传
                'buttonCursor': 'hand',
                'buttonClass' : '',
                'buttonText': '选择图标',
                'fileObjName' : 'Filedata',  // 后台参数名
                'fileSizeLimit' : '10MB',
                'fileTypeDesc' : '支持文件类型为BMP，JPG，JPEG，GIF，PNG，SVG',  // 文件类型描述
                'fileTypeExts' : '*.bmp; *.jpg; *.jpeg; *.gif; *.png; *.svg',  // 支持文件类型
                'formData': {'shopName': '${shopName}'},
                'height': 30,  // 选择按钮高度
                'itemTemplate': false,  // 为文件上传添加展示模板html
                'method': 'get',
                'multi': false,  // 是否允许文件多选
                'preventCaching': false,  // 是否关闭文件上传缓存功能
                'uploadLimit': 1,  // 上传文件个数的限制
                'swf': '${ctx }/static/plugins/uploadify/uploadify.swf',
                'checkExisting' : false,  // 检查文件重名时填写后台检测路径
                'uploader': '${ctx }/upload/springUploadFile;jsessionid=${pageContext.session.id }?secondPath=${shopId}'
            });
        });
    </script>
</body>
</html>

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
                'swf': '${ctx }/static/plugins/uploadify/uploadify.swf',
                'uploader': '${ctx }/upload/springUploadFile'
            });
        });
    </script>
</body>
</html>

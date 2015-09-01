<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Web Shop</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
</head>
<body>
    <div class="" id="redirectContent">
        已退出登录，正在等待跳转……
    </div>
</body>
<script>
    /**
    * 重定向跳转至登录页
     */
    function reIndex() {
        window.location.href = '${ctx }/loginPage';
    }

    // 页面初始化
    $(function() {
        setTimeout('reIndex()', 1500);
    });
</script>
</html>
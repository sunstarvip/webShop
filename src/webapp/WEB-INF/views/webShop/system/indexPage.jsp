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
<div class="" id="header">
    <%--<button class="" id="registerBtn" type="button">注册</button>--%>
    <button class="" id="loginBtn" type="button">登录</button>
    ${merchantAccount}
</div>

</body>
<script>


    // 页面初始化
    $(function() {
        $('#registerBtn').on('click', function() {
            window.location.href = "${ctx }/loginPage"
        });
        $('#loginBtn').on('click', function() {
            window.location.href = "${ctx }/loginPage"
        });
    });
</script>
</html>
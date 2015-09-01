<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺信息</title>
    <style>

        .shop-pic {
            width: 10em;
            height: 10em;
        }
    </style>
</head>
<body>
    <div class="main-body">
        <div class="title" id="title">
            店铺管理
        </div>
        <div class="content">
            <div class="shop-info">
                <div >
                    <img class="shop-pic" src="${ctx }/static/project/webShop/img/shangbiao.jpg">
                </div>
                <div class="shop-info-content">
                    <div id="shopName"></div>
                    <button class="" id="shopNameBtn">修改</button>
                </div>
                <div class="shop-info-content">
                    <div id="shopDesc"></div>
                    <button class="" id="shopDescBtn">修改</button>
                </div>
            </div>
            <div class="shop-func">
                <div class="shop-func-content">
                    <button class="" id="displayBtn">修改</button>
                </div>
            </div>
            <div class="shop-pay">
            </div>
        </div>
        <div class="footer">

        </div>
    </div>
    <script>
        function getShopInfo() {

        }

        // 页面初始化
        $(function() {

        });
    </script>
</body>
</html>

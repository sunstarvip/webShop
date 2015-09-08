<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <style>
        .user-opt {
            float: right;
            display: inline;
        }
        .user-nick {
            display: inline;
        }
    </style>
</head>
<body>
    <div class="main-body">
        <div class="header" id="header">
            <div class="user-opt">
                欢迎您，<div class="user-nick" id="userName"></div>
                <button class="" id="logoutBtn" type="button">登出</button>
            </div>
        </div>
        <div class="content">
            <button class="" id="shopBtn" type="button">店铺</button>
            <button class="" id="goodsTypeBtn" type="button">商品类型</button>
            <button class="" id="goodsBtn" type="button">商品</button>
            <button class="" id="orderBtn" type="button">订单</button>
        </div>
        <div class="footer">

        </div>
    </div>
    <script>
        userInfo = '${loginUserInfo }';

        function userInfoInit() {
            if(!!userInfo) {
                userInfo = eval('(' + userInfo + ')');
                var userName = '未知用户';
                if(!!userInfo['merchantName']) {
                    userName = userInfo['merchantName'];
                }else if(!!userInfo['merchantAccount']) {
                    userName = userInfo['merchantAccount'];
                }

                $('#userName').text(userName);
            }
        }

        // 页面初始化
        $(function() {
            userInfoInit();

            $('#logoutBtn').on('click', function() {
                window.location.href = '${ctx }/logout';
            });

            $('#shopBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/shop/managerPage';
            });

            $('#goodsTypeBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/goodsType/listPage';
            });

            $('#goodsBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/goods/createPage';
            });

            $('#orderBtn').on('click', function() {
                window.location.href = '${ctx }/';
            });
        });
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺信息</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <style>

        .shop-pic {
            width: 10em;
            height: 10em;
            margin: 5px;
        }
        .shop-info-content {
            margin: 5px;
        }
        .shop-func-content {
            margin: 5px;
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
                    <img class="shop-pic" id="shopPic">
                </div>
                <div class="shop-info-content">
                    <div id="shopName">未知</div>
                    <button class="" id="shopNameBtn">修改</button>
                </div>
                <div class="shop-info-content">
                    <div id="shopDesc">未知</div>
                    <button class="" id="shopDescBtn">修改</button>
                </div>
            </div>
            <div class="shop-func">
                <div class="shop-func-content">
                    <div id="goodsDisplay">未知</div>
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
        shopInfo = {};

        function initShopInfo() {
            $('#shopPic').attr('src', '${ctx }' + shopInfo['picUrl']);
            $('#shopName').text(shopInfo['name']);
            $('#shopDesc').text(shopInfo['description']);
        }

        function getShopInfo() {
            $.get('${ctx }/webShop/shop/managerShop',
                    function(resultData) {
                        if(!!resultData) {
                            resultData = eval('(' + resultData + ')');
                            if(!!resultData['status'] && resultData['status'] == 'success') {
                                shopInfo = eval('(' + resultData['dataInfo'] + ')');

                                // 初始化店铺信息
                                initShopInfo();
                            }
                        }
                    });
        }

        // 页面初始化
        $(function() {
            // 获取店铺信息
            getShopInfo();

            $('#shopNameBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/shop/editNamePage?shopId=' + shopInfo['id'];
            });
        });
    </script>
</body>
</html>

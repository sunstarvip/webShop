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
        .footer {
        }
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
        .shop-info-item {
            display: inline;
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
                    <label for="shopName">商品名称：</label>
                    <div class="shop-info-item" id="shopName">未知</div>
                    <button class="shop-info-item" id="shopNameBtn">修改</button>
                </div>
                <div class="shop-info-content">
                    <label for="shopDesc">商品描述：</label>
                    <div class="shop-info-item" id="shopDesc">未知</div>
                    <button class="shop-info-item" id="shopDescBtn">修改</button>
                </div>
            </div><hr>
            <div class="shop-func">
                <div class="shop-func-content">
                    <label for="displayMode">商品展示方式：</label>
                    <div class="shop-info-item" id="displayMode">未知</div>
                    dd
                </div>
                <div class="shop-func-content">
                    <label for="displayMode">商品付款方式：</label>
                    <div class="shop-info-item" id="payMode">未知</div>
                    <button class="shop-info-item" id="payModeBtn">修改</button>
                </div>
            </div><hr>
            <div class="shop-pay">
            </div>
        </div>
        <div class="footer">
            <button class="shop-info-item" id="backBtn">返回</button>
        </div>
    </div>
    <script>
        shopInfo = {};

        function initDisplayMode(displayMode) {
            if(!!displayMode) {
                var displayModeText = '未知';
                switch (displayMode) {
                    case 'by_date':
                        displayModeText = '按日期排列';
                        break;
                    case 'by_type':
                        displayModeText = '按类型排列';
                        break;
                }

                $('#displayMode').text(displayModeText);
            }
        }

        function initPayMode(payMode) {
            if(!!payMode) {
                var payModeText = '未知';
                switch (payMode) {
                    case 'in_site':
                        payModeText = '站内购买';
                        break;
                    case 'out_site':
                        payModeText = '其他站点购买';
                        break;
                }

                $('#payMode').text(payModeText);
            }
        }

        function initShopInfo() {
            $('#shopPic').attr('src', '${ctx }' + shopInfo['picUrl']);
            $('#shopName').text(shopInfo['name']);
            $('#shopDesc').text(shopInfo['description']);
            initDisplayMode(shopInfo['displayMode']);
            initPayMode(shopInfo['payMode']);
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

            // 修改店铺名称
            $('#shopNameBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/shop/editNamePage?shopId=' + shopInfo['id'];
            });
            // 修改店铺描述
            $('#shopDescBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/shop/editDescPage?shopId=' + shopInfo['id'];
            });

            // 返回首页
            $('#backBtn').on('click', function() {
                window.location.href = '${ctx }/indexPage';
            });
        });
    </script>
</body>
</html>

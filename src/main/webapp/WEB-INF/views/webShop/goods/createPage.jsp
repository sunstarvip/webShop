<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/project/base/restful.js"></script>
    <style>
        .shop-form-item {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="shop-form" id="goodsForm">
        <div class="shop-form-item">
            <label for="name">商品名称</label>
            <input class="shop-form-input" id="name" name="name">
        </div>
        <div class="shop-form-item">
            <label for="description">商品描述</label>
            <textarea class="shop-form-textarea" id="description" name="description"></textarea>
        </div>
        <div class="shop-form-item">
            <label for="buyLink">购买链接</label>
            <input class="shop-form-input" id="buyLink" name="buyLink">
        </div>
        <div class="shop-form-item">
            <label for="goodsPrice">商品价格</label>
            <input class="shop-form-input" id="goodsPrice" name="goodsPrice">
        </div>
        <div class="shop-form-item">
            <label for="stockNum">库存数量</label>
            <input class="shop-form-input" id="stockNum" name="stockNum">
        </div>
        <div class="shop-form-footer">
            <button class="shop-form-subbmit" id="createBtn" type="submit">提交</button>
        </div>
    </div>

    <script>
        function saveGoods() {
            $.post('${ctx }/webShop/goods/saveGoods',
                    {
                        name: $('#name').val(),
                        description: $('#description').val(),
                        buyLink: $('#buyLink').val(),
                        goodsPrice: $('#goodsPrice').val(),
                        stockNum: $('#stockNum').val()
                    },
                    function(resultData) {
                        if(ResultData.getSuccessStatus(resultData)) {
                            alert("商品添加成功");
                            window.location.href = '${ctx }/indexPage';
                        }
                    });
        }

        // 页面初始化
        $(function() {
            // 提交编辑
            $('#createBtn').on('click', function() {
                saveGoods();
            });
        });
    </script>
</body>
</html>

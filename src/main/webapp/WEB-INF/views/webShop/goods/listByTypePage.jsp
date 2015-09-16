<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商品类型管理</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/project/base/restful.js"></script>
    <style>
        .shop-list-item {
            margin: 5px;
            border: 1px solid #000;
        }
        .shop-list-item-label {
            margin-right: 15px;
        }
    </style>
</head>
<body>
    <div class="shop-list-header">
        <button class="shop-list-button" id="backBtn" type="button">返回</button>
    </div>
    <div class="shop-list" id="goodsTypeList">
        <div class="shop-list-item">
            <a class="" href="${ctx }/page/goods/listByDatePage" target="_blank">
                <span class="shop-list-item-label">运动类</span>
                <span class="shop-list-item-info">商品数量为：10个</span>
            </a>
        </div>
        <div class="shop-list-item">
            <span class="shop-list-item-label">运动类</span>
            <span class="shop-list-item-info">商品数量为：10个</span>
        </div>
        <div class="shop-list-item">
            <span class="shop-list-item-label">运动类</span>
            <span class="shop-list-item-info">商品数量为：10个</span>
        </div>
    </div>
    <div class="shop-list-footer">
        <button class="shop-list-button" id="createBtn" type="button">新增</button>
        <button class="shop-list-button" id="editBtn" type="button">编辑</button>
    </div>

    <script>
        function makeGoodsTypeList(goodsTypeList) {
            var htmlStr = '';

            for(var i in goodsTypeList) {
                htmlStr += '<div class="shop-list-item">';
                htmlStr += '<a class="" href="${ctx }/page/goods/listByDatePage?goodsTypeId='+1+'" target="_blank">';
                htmlStr += '<span class="shop-list-item-label">运动类</span>';
                htmlStr += '<span class="shop-list-item-info">商品数量为：10个</span>';
                htmlStr += '</a>';
                htmlStr += '</div>';
            }

            $('#goodsTypeList').html(htmlStr);
        }

        function getGoodsList() {
            $.get('${ctx }/rest/goods/getGoodsList',
                    function(resultData) {
                        var goodsList = ResultData.getSuccessDataInfo(resultData);
                        if(!!goodsList) {
                            goodsList = eval('(' + goodsList + ')');
                            makeGoodsTypeList(goodsList);
                        }
                    });
        }

        // 页面初始化
        $(function() {
            // 生成GoodsType列表
//            getGoodsList();

            // 返回首页
            $('#backBtn').on('click', function() {
                window.location.href = '${ctx }/indexPage';
            });
        });
    </script>
</body>
</html>

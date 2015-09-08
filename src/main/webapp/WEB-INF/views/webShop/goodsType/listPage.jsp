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
    </style>
</head>
<body>
    <div class="shop-list" id="goodsTypeList">
        <div class="shop-list-item">
            <input class="shop-list-checkbox" name="goodsTypeCkb" type="checkbox" value="1">
            <span>类型1</span>
        </div>
        <div class="shop-list-item">
            <input class="shop-list-checkbox" name="goodsTypeCkb" type="checkbox" value="2">
            <span>类型2</span>
        </div>
        <div class="shop-list-item">
            <input class="shop-list-checkbox" name="goodsTypeCkb" type="checkbox" value="3">
            <span>类型3</span>
        </div>
    </div>
    <div class="shop-list-footer">
        <button class="shop-list-button" id="createBtn" type="button">新建</button>
        <button class="shop-list-button" id="editBtn" type="button">编辑</button>
    </div>

    <script>
        function makeGoodsTypeList(typeList) {
            var htmlStr = '';

            for(var goodsType in typeList) {
                htmlStr += '<div class="shop-list-item">';
                htmlStr += '<input class="shop-list-checkbox" name="goodsTypeCkb" type="checkbox" value="1">';
                htmlStr += '<span>类型1</span>';
                htmlStr += '</div>';
            }

            $('#goodsTypeList').html(htmlStr);
        }

        function getGoodsTypeList() {
            $.get('${ctx }/webShop/goodsType/getGoodsTypeList',
                    function(resultData) {
                        var typeList = ResultData.getSuccessDataInfo(resultData);
                        if(!!typeList) {
                            typeList = eval('(' + typeList + ')');
                            makeGoodsTypeList(typeList);
                        }
                    });
        }

        // 页面初始化
        $(function() {
            // 生成GoodsType列表
            getGoodsTypeList();

            // 新增商品类型
            $('#createBtn').on('click', function() {
                window.location.href = '${ctx }/webShop/goodsType/createPage';
            });
        });
    </script>
</body>
</html>

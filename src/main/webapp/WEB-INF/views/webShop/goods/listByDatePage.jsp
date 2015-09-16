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
    <div class="shop-list-header">
        <button class="shop-list-button" id="backBtn" type="button">返回</button>
    </div>
    <div class="shop-list" id="goodsList">
    </div>
    <div class="shop-list-footer">
        <button class="shop-list-button" id="createBtn" type="button">新增</button>
        <button class="shop-list-button" id="editBtn" type="button">编辑</button>
    </div>

    <script>
        function makeGoodsList(goodsList) {
            var htmlStr = '';

            for(var i in goodsList) {
                htmlStr += '<div class="shop-list-item">';
                htmlStr += '<input class="shop-list-checkbox" name="goodsChk" type="checkbox" value="' + goodsList[i]['id'] + '">';
                htmlStr += '<span>' + goodsList[i]['name'] + '</span>';
                htmlStr += '</div>';
            }

            $('#goodsList').html(htmlStr);
        }

        function getGoodsList() {
            $.get('${ctx }/rest/goods/getGoodsList',
                    function(resultData) {
                        var goodsList = ResultData.getSuccessDataInfo(resultData);
                        if(!!goodsList) {
                            goodsList = eval('(' + goodsList + ')');
                            makeGoodsList(goodsList);
                        }
                    });
        }

        function validateGoodsChk() {
            var goodsAry = $('input[name="goodsChk"]:checked');

            if(!!goodsAry) {
                if(goodsAry.length > 1) {
                    alert("请勿多选");
                    return;
                }else {
                    return goodsAry[0];
                }
            }
            alert("请选择商品");
            return;
        }

        function updateGoods() {
            var goods = validateGoodsChk();
            if(!!goods) {
                window.location.href = '${ctx }/page/goods/updatePage?goodsId=' + $(goods).val();
            }
        }

        // 页面初始化
        $(function() {
            // 生成GoodsType列表
            getGoodsList();

            // 返回首页
            $('#backBtn').on('click', function() {
                window.location.href = '${ctx }/indexPage';
            });

            // 新增商品类型
            $('#createBtn').on('click', function() {
                window.location.href = '${ctx }/page/goods/createPage';
            });

            // 编辑商品类型
            $('#editBtn').on('click', function() {
                updateGoods();
            });
        });
    </script>
</body>
</html>

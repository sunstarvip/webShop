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
        .float-right {
            float: right;
        }
        .shop-form-item {
            margin: 5px;
        }
        .shop-form-textarea {
            vertical-align: top;
        }
        .goods-mode-div {
            display: none;
            border: 1px solid #000;
        }
        .mode-content-div {
            border: 1px dashed #000;
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
            <label for="goodsType">商品类型</label>
            <select class="shop-form-select" id="goodsType" name="goodsType">
                <option value="">---请选择---</option>
            </select>
        </div>
        <div class="shop-form-item">
            <label for="buyLink">购买链接</label>
            <input class="shop-form-input" id="buyLink" name="buyLink">
        </div>
        <div class="" id="goodsModeDiv">
            <div class="goods-mode-div" id="modeDiv">
                <%--<div class="mode-content-div">--%>
                    <%--<button class="shop-form-button float-right" id="deleteGoodsModeBtn-1" name="deleteGoodsModeBtn" type="button">删除</button>--%>
                    <%--<div class="shop-form-item">--%>
                        <%--<label for="modeName1">型号名称</label>--%>
                        <%--<input class="shop-form-input" id="modeName1" name="modeName1">--%>
                    <%--</div>--%>
                    <%--<div class="shop-form-item">--%>
                        <%--<label for="modePrice1">型号价格</label>--%>
                        <%--<input class="shop-form-input" id="modePrice1" name="modePrice1">元--%>
                    <%--</div>--%>
                    <%--<div class="shop-form-item">--%>
                        <%--<label for="stockNum1">库存数量</label>--%>
                        <%--<input class="shop-form-input" id="stockNum1" name="stockNum1">个--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="" id="defaultModeDiv">
                <div class="shop-form-item">
                    <label for="goodsPrice">商品价格</label>
                    <input class="shop-form-input" id="goodsPrice" name="goodsPrice">元
                </div>
                <div class="shop-form-item">
                    <label for="stockNum">库存数量</label>
                    <input class="shop-form-input" id="stockNum" name="stockNum">个
                </div>
            </div>
            <div class="shop-form-item">
                <button class="shop-form-button" id="addGoodsModeBtn" type="button">增加型号</button>
            </div>
        </div>

        <div class="shop-form-footer">
            <button class="shop-form-subbmit" id="createBtn" type="submit">提交</button>
        </div>
    </div>

    <script>
        modeCount = 0;  // 商品型号总数
        modeNum = 0;  // 商品型号计数

        function makeGoodsType(typeList) {
            var htmlStr = '';

            for(var goodsType in typeList) {
                htmlStr += '<option value="">---请选择---</option>';
            }

            $('#goodsType').append(htmlStr);
        }

        function getGoodsType() {
            $.get('${ctx }/webShop/goodsType/getGoodsTypeList',
                    function(resultData) {
                        var typeList = ResultData.getSuccessDataInfo(resultData);
                        if(!!typeList) {
                            typeList = eval('(' + typeList + ')');
                            makeGoodsType(typeList);
                        }
                    });
        }

        function addGoodsMode() {
            if(modeCount == 0) {
                $('#goodsPrice').val('');
                $('#stockNum').val('');
                $('#defaultModeDiv').hide();
                $('#modeDiv').show();
            }
            modeCount++;
            modeNum++;
            var htmlStr = '';

            htmlStr += '<div class="mode-content-div">';
            htmlStr += '<button class="shop-form-button float-right" id="deleteGoodsModeBtn-'+modeNum+'" name="deleteGoodsModeBtn" type="button">删除</button>';
            htmlStr += '<div class="shop-form-item">';
            htmlStr += '<label for="modeName'+modeNum+'">型号名称</label>';
            htmlStr += '<input class="shop-form-input" id="modeName'+modeNum+'" name="modeName'+modeNum+'">';
            htmlStr += '</div>';
            htmlStr += '<div class="shop-form-item">';
            htmlStr += '<label for="modePrice'+modeNum+'">型号价格</label>';
            htmlStr += '<input class="shop-form-input" id="modePrice'+modeNum+'" name="modePrice'+modeNum+'">元';
            htmlStr += '</div>';
            htmlStr += '<div class="shop-form-item">';
            htmlStr += '<label for="stockNum'+modeNum+'">库存数量</label>';
            htmlStr += '<input class="shop-form-input" id="stockNum'+modeNum+'" name="stockNum'+modeNum+'">个';
            htmlStr += '</div>';
            htmlStr += '</div>';

            $('#modeDiv').append(htmlStr);
        }

        function deleteGoodsMode() {
            modeCount--;
            $(this).parent().remove();
            if(modeCount == 0) {
                $('#modeDiv').hide();
                $('#defaultModeDiv').show();
            }
        }

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
            // 增加商品类型
            $('#addGoodsModeBtn').on('click', function() {
                addGoodsMode();
            });
            // 删除商品类型
            $(document).on('click', '[name=deleteGoodsModeBtn]', function() {
                deleteGoodsMode();
            });
            // 提交新增
            $('#createBtn').on('click', function() {
                saveGoods();
            });
        });
    </script>
</body>
</html>

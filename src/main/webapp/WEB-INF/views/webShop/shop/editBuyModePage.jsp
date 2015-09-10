<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺管理</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/core/js/utilTools.js"></script>
    <style>
        .shop-form-item {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="shop-form" id="buyModeForm">
        <div class="shop-form-item">
            <label for="buyMode">商品购买方式：</label>
            <select class="" id="buyMode" name="buyMode" >
                <%--<option value="in_site">站内付款</option>--%>
                <option value="out_site">其他站点购买</option>
            </select>
        </div>
        <div class="shop-form-footer">
            <button class="shop-form-subbmit" id="saveBtn" type="button">提交</button>
            <button class="shop-form-button" id="cancelBtn" type="button">取消</button>
        </div>
    </div>

    <script>
        function editShopBuyMode() {
            var shopObj = {};
            shopObj['buyMode'] = $('#buyMode').val();

            formSubmit('${ctx }/webShop/shop/editBuyMode', 'post', '_self', shopObj);
        }

        function initBuyModeSelect() {
            $('#buyMode').val('${shopBuyMode }');
        }

        // 页面初始化
        $(function() {
            // 初始化购买方式下拉列表
            initBuyModeSelect();

            // 提交编辑
            $('#saveBtn').on('click', function() {
                editShopBuyMode();
            });

            // 取消编辑
            clickPageForwardBtn('#cancelBtn', '${ctx }/webShop/shop/managerPage');
        });
    </script>
</body>
</html>

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
    <div class="shop-form" id="displayModeForm">
        <div class="shop-form-item">
            <label for="displayMode">商品购买方式：</label>
            <select class="" id="displayMode" name="displayMode" >
                <option value="by_date">按日期排列</option>
                <option value="by_type">按类型排列</option>
            </select>
        </div>
        <div class="shop-form-footer">
            <button class="shop-form-subbmit" id="saveBtn" type="button">提交</button>
            <button class="shop-form-button" id="cancelBtn" type="button">取消</button>
        </div>
    </div>

    <script>
        function editShopDisplayMode() {
            var shopObj = {};
            shopObj['displayMode'] = $('#displayMode').val();

            formSubmit('${ctx }/page/shop/editDisplayMode', 'post', '_self', shopObj);
        }

        function initDisplayModeSelect() {
            $('#displayMode').val('${shopDisplayMode }');
        }

        // 页面初始化
        $(function() {
            // 初始化展示方式下拉列表
            initDisplayModeSelect();

            // 提交编辑
            $('#saveBtn').on('click', function() {
                editShopDisplayMode();
            });

            // 取消编辑
            clickPageForwardBtn('#cancelBtn', '${ctx }/page/shop/managerPage');
        });
    </script>
</body>
</html>
